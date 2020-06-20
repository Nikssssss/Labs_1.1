import java.io.IOException;

public class ClientMain {
    public static void main(String[] args){
        TcpClient client = null;
        try {
            client = new TcpClient("127.0.0.1", 2048);
        } catch (IOException e) {
            System.out.println("Client hasn't been created");
        }
        ClientViewer clientViewer = new ClientViewer(client);
        client.addObserver(clientViewer);
        clientViewer.makeConnection();
    }
}
