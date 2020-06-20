import observers.Observer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientViewer implements Observer {
    private TcpClient client;
    private BufferedReader bufferedReader;

    public ClientViewer(TcpClient client) {
        this.client = client;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void readConsoleMessages() throws IOException {
        while (client.isActive()){
            String message = bufferedReader.readLine();
            client.analyzeMessage(message);
        }
    }

    public void makeConnection(){
        String name;
        System.out.println("Enter your name:");
        try {
            name = bufferedReader.readLine();
            while(client.makeConnection(name)){
                System.out.println("User with this name already exists. Choose another name:");
                name = bufferedReader.readLine();
            }
            System.out.println("Welcome, " + name + "!");
            readConsoleMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
