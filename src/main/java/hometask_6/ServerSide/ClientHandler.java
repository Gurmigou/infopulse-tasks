package hometask_6.ServerSide;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class ClientHandler implements Runnable {
    private final Server server;
    private final Socket clientSocket;
    private final int clientId;
    private static int nextId = 1;

    private final PrintWriter writer;
    private final Scanner reader;

    private String clientName;
    private int chatId;

    {
        this.clientId = nextId++;
    }

    public ClientHandler(Server server, Socket clientSocket) throws IOException {
        this.server = server;
        this.clientSocket = clientSocket;

        this.writer = new PrintWriter(new OutputStreamWriter(
                clientSocket.getOutputStream(), UTF_8), true);

        this.reader = new Scanner(clientSocket.getInputStream(), UTF_8);
    }

    private void readClientName() {
        writer.println("Enter your name: ");
        this.clientName = reader.nextLine();
    }

    private void readChatId() {
        writer.println("Enter chat id to connect (or \"anything\" to create new chat): ");
        String chatId = reader.nextLine();

        try {
            int parsedChatId = Integer.parseInt(chatId);
            if (server.chatIdExists(parsedChatId))
                this.chatId = parsedChatId;
            else
                this.chatId = server.generateChatId();

        } catch (Exception e) {
            this.chatId = server.generateChatId();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return clientId == that.clientId &&
                chatId == that.chatId &&
                clientName.equals(that.clientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientName, chatId);
    }

    @Override
    public void run() {
        readClientName();
        readChatId();

        while (true) {
            String clientMessage = reader.nextLine();

            // клиент выходит из чата
            if (clientMessage.equalsIgnoreCase("leave")) {
                server.disconnectUser(this);
                break;
            }

            server.sendMessageToAllChatMembers(this.chatId, this.clientName + ": " + clientMessage);
        }
    }

    public int getChatId() {
        return chatId;
    }

    public void sendMessage(String message) {
        this.writer.println(message);
    }
}
