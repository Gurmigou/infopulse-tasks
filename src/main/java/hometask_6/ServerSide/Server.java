package hometask_6.ServerSide;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int port;
    private final Set<ClientHandler> clientSet = new HashSet<>();
    private final Set<Integer> existingChatIds = new ConcurrentSkipListSet<>();

    private static int nextChatId = 1;

    public Server(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        ExecutorService es = Executors.newFixedThreadPool(100);

        // установить сокет на стороне сервера
        try (ServerSocket serverSocket = new ServerSocket(port))
        {
            // ожидать подключения на стороне клиента
            while (true) {
                try (Socket incoming = serverSocket.accept()) {
                    ClientHandler clientHandler = new ClientHandler(this, incoming);
                    clientSet.add(clientHandler);
                    es.submit(clientHandler);
                }
            }
        }
    }

    protected synchronized int generateChatId() {
        this.existingChatIds.add(nextChatId);
        return nextChatId++;
    }

    protected synchronized boolean chatIdExists(int chatId) {
        return existingChatIds.contains(chatId);
    }

    protected synchronized void disconnectUser(ClientHandler clientHandler) {
        clientSet.remove(clientHandler);
        long chatMembers = clientSet.stream()
                .filter(c -> c.getChatId() == clientHandler.getChatId())
                .count();
        if (chatMembers == 0)
            existingChatIds.remove(clientHandler.getChatId());
    }

    protected synchronized void sendMessageToAllChatMembers(int chatId, String message) {
        clientSet.stream()
                .filter(c -> c.getChatId() == chatId)
                .forEach(c -> c.sendMessage(message));
    }
}
