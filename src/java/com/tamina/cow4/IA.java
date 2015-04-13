package tamina.cow4;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tamina.cow4.message.AuthenticateAbstractMessage;
import tamina.cow4.message.AuthenticateResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class IA {

    private static final Logger LOG = LoggerFactory.getLogger(IA.class);

    private static final String       EOF    = "#end#";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private InputStream  inStream;
    private OutputStream outStream;
    private Socket server;
    private long   connectId;

    public IA(String address, int port) {
        try {
            LOG.info("Connect to COW server : {}:{}", address, port);
            this.server     = new Socket(address, port);
            this.inStream   = server.getInputStream();
            this.outStream  = server.getOutputStream();
        } catch (IOException e) {
            LOG.error("Can't connect to COW server : {}", e.getMessage());
        }
    }

    public IA authenticate(String name, String avatarUrl) {
        try {
            AuthenticateAbstractMessage auth = new AuthenticateAbstractMessage(name, avatarUrl);
            outStream.write((MAPPER.writeValueAsString(auth) + EOF).getBytes());
            outStream.flush();
            connectId = MAPPER.readValue(inStream, AuthenticateResponse.class).getId();
            System.out.println("Connect with id : " + connectId);
            LOG.info("Authenticate : with name : {} and id : {}", name, connectId);
        } catch (IOException e) {
            LOG.info("Can't authenticate : {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return this;
    }

    public void close() {
        try {
            this.inStream.close();
            this.outStream.close();
            this.server.close();
            LOG.info("Close server");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public long getConnectId() {
        return connectId;
    }
}
