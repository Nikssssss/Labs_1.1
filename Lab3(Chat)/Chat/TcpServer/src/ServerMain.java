public class ServerMain {
    public static void main(String[] args){
        try {
            TcpServer server = new TcpServer(2048);
            Thread threadServer = new Thread(server);
            threadServer.start();
            threadServer.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
