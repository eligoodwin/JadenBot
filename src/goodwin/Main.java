package goodwin;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        JadenServer jadenServer = new JadenServer();
        jadenServer.listenForConnections();
    }
}
