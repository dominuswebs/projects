import javax.swing.*; // Provides a set of "lightweight" (all-Java language) components that, to the maximum degree possible, work the same on all platforms.
import java.awt.*; // API to develop Graphical User Interface (GUI) or windows-based applications

import java.io.*; // writing
import java.net.InetSocketAddress;
import java.nio.channels.*; // network connections

import static java.nio.charset.StandardCharsets.UTF_8;

public class SimpleChatClient {
    private JTextField outgoing; // swing
    private PrintWriter writer; // io

    public void go() {
        setUpNetworking();

        outgoing = new JTextField(20); // swing

        JButton sendButton = new JButton("Send"); // swing
        sendButton.addActionListener(e -> sendMessage()); // swing

        JPanel mainPanel = new JPanel(); // swing
        mainPanel.add(outgoing); // awt - there is no swing option that does this
        mainPanel.add(sendButton); // awt - there is no swing option that does this

        JFrame frame = new JFrame("Simple chat client"); // swing
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel); // swing and awt
        frame.setSize(400, 100); // awt
        frame.setVisible(true); // awt
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // swing
    }

    private void setUpNetworking() {
        try {
            InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000); // net
            // Open a SocketChannel that connects to the server.
            SocketChannel socketChannel = SocketChannel.open(serverAddress); // nio
            // This is where we make the PrintWriter from a writer that writes to the SocketChannel
            writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8)); // io
            System.out.println("Connection established.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        // Now we actually do the writing. Remember, the writer is chained to the writer from the SocketChannel,
        // so whenever we do a println(), it goes over the network to the server!
        writer.println(outgoing.getText()); // io and swing
        writer.flush(); // io
        outgoing.setText(""); // swing
        outgoing.requestFocus(); // swing
    }

    public static void main(String[] args) {
        new SimpleChatClient().go();
    }
}
