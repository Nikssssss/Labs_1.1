import observers.Observable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TcpClient extends Observable implements Runnable {
    private Socket socket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private boolean isActive = true;

    public TcpClient(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        writer = new ObjectOutputStream(socket.getOutputStream());
        reader = new ObjectInputStream(socket.getInputStream());
    }

    public boolean makeConnection(String name){
        try {
            writer.writeObject(new Message(MessageType.REGISTRATION_ATTEMPT, name));
            Message replyMessage = (Message) reader.readObject();
            if (replyMessage.getMessageType() == MessageType.REGISTRATION_SUCCESS){
                Thread threadClient = new Thread(this);
                threadClient.start();
                return false;
            }
            else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void analyzeMessage(String message){
        if (message.startsWith("/")){
            if (message.substring(1).equals("exit")){
                isActive = false;
            }
            try {
                writer.writeObject(new Message(MessageType.COMMAND, message.substring(1)));
                writer.flush();
            } catch (IOException e) {
                System.out.println("Server was shutdown");
                isActive = false;
            }
        }
        else if (!message.equals("\n")){
            try {
                writer.writeObject(new Message(MessageType.MESSAGE_REQUEST, message));
                writer.flush();
            } catch (IOException e) {
                System.out.println("Server was shutdown");
                isActive = false;
            }
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = (Message) reader.readObject();
                if (message.getMessageType() == MessageType.EXIT_RESPONSE){
                    socket.close();
                    return;
                }
                notifyObservers(message.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Server was shutdown. Enter anything to exit.");
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
