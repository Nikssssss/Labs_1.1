import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientProcessor implements Runnable {
    private Socket socket;
    private TcpServer server;
    private String clientName;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public ClientProcessor(Socket socket, TcpServer tcpServer) {
        this.socket = socket;
        this.server = tcpServer;
        try {
            reader = new ObjectInputStream(socket.getInputStream());
            writer = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            //log
        }
    }

    public void close(){
        if (socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                //log
            }
        }
    }

    public void sendMessage(Message message){
        try {
            writer.writeObject(message);
            writer.flush();
        } catch (IOException e) {
            //log
        }
    }

    private void analyzeCommandMessage(Message message){
        if (message.getMessage().equals("exit")){
            Message responseToAll = new Message(MessageType.MESSAGE_RESPONSE, clientName + " has left the chat");
            Message responseToExited = new Message(MessageType.EXIT_RESPONSE, "");
            try {
                server.sendMessage(responseToAll);
                writer.writeObject(responseToExited);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            server.removeClient(this);
        }
        else if (message.getMessage().equals("users")){
            Message response = new Message(MessageType.USERS_RESPONSE, "Online: " + server.getClientsNames());
            try {
                writer.writeObject(response);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                Message request = (Message) reader.readObject();
                if (request.getMessageType() == MessageType.REGISTRATION_ATTEMPT){
                    Message response;
                    if (server.isAvailableName(request.getMessage())){
                        response = new Message(MessageType.REGISTRATION_SUCCESS, "");
                        writer.writeObject(response);
                        writer.flush();
                        clientName = request.getMessage();
                        server.addClient(this);
                    }
                    else {
                        response = new Message(MessageType.REGISTRATION_DENY, "");
                        writer.writeObject(response);
                        writer.flush();
                    }
                }
                else if (request.getMessageType() == MessageType.MESSAGE_REQUEST){
                    Message response = new Message(MessageType.MESSAGE_RESPONSE, clientName + ": " + request.getMessage());
                    server.sendMessage(response);
                }
                else if (request.getMessageType() == MessageType.COMMAND){
                    analyzeCommandMessage(request);
                }
            }
        } catch (EOFException e) {
            System.out.println("Client has been disconnected");
        } catch (Exception e) {
            System.out.println("Client " + clientName + " has been interrupted");
            server.removeClient(this);
        }
    }

    public String getClientName() {
        return clientName;
    }
}
