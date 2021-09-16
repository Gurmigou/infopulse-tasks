package hometask_6.ClientSide;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 3443;

    private Socket clientSocket;
    private PrintWriter writer;
    private Scanner reader;

    public Client() {
    }

    public void connect() throws IOException {
        this.clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
        this.writer = new PrintWriter(new OutputStreamWriter(
                clientSocket.getOutputStream(), UTF_8), true);
        this.reader = new Scanner(clientSocket.getInputStream(), UTF_8);

        while (true) {
            if (reader.hasNextLine()) {
                String inputMessage = reader.nextLine();

            }
            if (writer.)
        }
    }


}
