import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TcpServer implements Runnable{
    private int port;
    private ServerSocket serverSocket;
    private List<ClientProcessor> clients = Collections.synchronizedList(new LinkedList<>());

    public TcpServer(int port) {
        this.port = port;
    }

    public void close(){
        if (serverSocket != null){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (ClientProcessor client : clients){
            client.close();
        }
    }

    public String getClientsNames(){
        String names = "";
        for (var client : clients){
            names = names.concat(client.getClientName() + ", ");
        }
        if (!names.equals("")){
            names = names.substring(0, names.length() - 2);
        }
        return names;
    }

    public void sendMessage(Message message){
        for (var client : clients){
            client.sendMessage(message);
        }
    }

    public void addClient(ClientProcessor client){
        clients.add(client);
    }

    public void removeClient(ClientProcessor client){
        clients.remove(client);
    }

    public boolean isAvailableName(String name){
        for (var client : clients){
            if (client.getClientName().equals(name)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server has been created");
            while (Thread.currentThread().isAlive()){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Server has accepted client: " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
                ClientProcessor client = new ClientProcessor(clientSocket, this);
                Thread clientProcessor = new Thread(client);
                clientProcessor.start();
            }
        } catch (Exception e) {
            this.close();
            e.printStackTrace();
        }
    }
}
