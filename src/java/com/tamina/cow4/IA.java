package tamina.cow4;

import com.google.common.collect.Lists;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tamina.cow4.message.AuthenticateAbstractMessage;
import tamina.cow4.message.AuthenticateResponse;
import tamina.cow4.message.TurnAction;
import tamina.cow4.message.TurnResult;
import tamina.cow4.model.IAInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class IA {

    private static final Logger LOG = LoggerFactory.getLogger(IA.class);

    private static final String       EOF    = "#end#";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private InputStream  inStream;
    private OutputStream outStream;
    private Socket server;
    private IAInfo iaInfo;


    public IA(String address, int port) {
        try {
            LOG.info("Connect to COW server : {}:{}", address, port);
            this.server     = new Socket(address, port);
            this.inStream   = server.getInputStream();
            this.outStream  = server.getOutputStream();
            MAPPER.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, false);
        } catch (IOException e) {
            LOG.error("Can't connect to COW server : {}", e.getMessage());
        }
    }

    public IA authenticate(String name, String avatarUrl) {
        try {
            AuthenticateAbstractMessage auth = new AuthenticateAbstractMessage(name, avatarUrl);
            outStream.write((MAPPER.writeValueAsString(auth) + EOF).getBytes());
            outStream.flush();
            float id = MAPPER.readValue(inStream, AuthenticateResponse.class).getId();
            iaInfo = new IAInfo(id, name, avatarUrl);
            LOG.info("Authenticate : {} ", iaInfo);
        } catch (IOException e) {
            LOG.error("Can't authenticate : {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return this;
    }

    public IA handleMessage(ProcessTurn func){
        new Thread(() -> {
                while(true) {
                    try {
                        TurnAction turnAction = MAPPER.readValue(server.getInputStream(), TurnAction.class);
                        List<TurnAction> turnActions = func.processTurn(turnAction.getData());
                        TurnResult result = new TurnResult(iaInfo, turnActions);
                        outStream.write((MAPPER.writeValueAsString(result) + EOF).getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).run();
        return this;
    }

    public void close() {
        try {
            this.inStream.close();
            this.outStream.close();
            this.server.close();
            LOG.info("Close server");
        } catch (IOException e) {
            LOG.error("Error while closing the server : {}", e.getMessage());
        }
    }

}
