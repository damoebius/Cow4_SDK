package tamina.cow4;

import org.codehaus.jackson.map.ObjectMapper;
import tamina.cow4.message.AuthenticateAbstractMessage;
import tamina.cow4.message.AuthenticateResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class IA {

    private static final String       EOF    = "#end#";
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private DataInputStream  inStream;
    private DataOutputStream outStream;
    private Socket server;
    private long   connectId;

    public IA(String address, int port) {
        try {
            this.server     = new Socket(address, port);
            this.inStream   = new DataInputStream(server.getInputStream());
            this.outStream  = new DataOutputStream(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IA connect(String name, String avatarUrl) {
        try {
            AuthenticateAbstractMessage auth = new AuthenticateAbstractMessage(name, avatarUrl);
            outStream.write((MAPPER.writeValueAsString(auth) + EOF).getBytes());
            outStream.flush();
            connectId = MAPPER.readValue(inStream, AuthenticateResponse.class).getId();
            System.out.println("Connect with id : " + connectId);
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
            throw new RuntimeException(e);
        }
        return this;
    }

    public void close() {
        try {
            this.inStream.close();
            this.outStream.close();
            this.server.close();
            System.out.println("Close server socket");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public long getConnectId() {
        return connectId;
    }
}
