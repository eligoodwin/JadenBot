package goodwin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class JadenServer {
    private static final int PORT = 44444;
    private static  ServerSocket serverSocket;
    private Thread jadenThread;

    public JadenServer(){
        try {
            this.serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenForConnections() throws IOException {
        while(true) {
            Socket clientSocket = serverSocket.accept();
            new ClientThread(clientSocket).start();
        }
    }

    public void killJadenServer() throws IOException {
        serverSocket.close();
    }
}
