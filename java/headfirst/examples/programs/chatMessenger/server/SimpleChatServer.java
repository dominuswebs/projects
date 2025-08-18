import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.*;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatServer {
    private final List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        new SimpleChatServer().go();
    }

    public void go() {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {

            // ServerSocketChannel.open() is a static method call. It internally uses the new keyword (or other instantiation mechanisms) to create a new ServerSocketChannel instance.
            // The method returns a fully constructed and initialized ServerSocketChannel object.
            // You assign this object to the serverSocketChannel variable.
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(5000));

            while (serverSocketChannel.isOpen()) {
                SocketChannel clientSocket = serverSocketChannel.accept();
                PrintWriter writer = new PrintWriter(Channels.newWriter(clientSocket, UTF_8));
                clientWriters.add(writer);
                threadPool.submit(new ClientHandler(clientSocket));
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void tellEveryone(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
            writer.flush();
        }
    }

    public class ClientHandler implements Runnable {
        BufferedReader reader;
        SocketChannel socket;

        public ClientHandler(SocketChannel clientSocket) {
            socket = clientSocket;
            reader = new BufferedReader(Channels.newReader(socket, UTF_8));
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}