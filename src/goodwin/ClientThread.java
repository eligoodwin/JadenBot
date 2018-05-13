package goodwin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private JadenSmithBot jadenBot;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.jadenBot = new JadenSmithBot();
    }

    @Override
    public void run(){
        super.run();
        //get random strings and send them to the user
        while(clientSocket.isConnected()){
            //Todo: implement authentication here -- anyone can talk to the robot from the app
            String message = jadenBot.getRandomGarbage();
            try {
                Random random = new Random();
                Thread.sleep(1000 * random.nextInt(15));
                sendMessage(message);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) throws IOException {
        PrintWriter out = writeToBuffer(clientSocket);
        out.println(message + "\n");
    }

    private PrintWriter writeToBuffer(Socket clientSocket) throws IOException {
        OutputStream out = clientSocket.getOutputStream();
        return new PrintWriter(out, true);
    }

}

