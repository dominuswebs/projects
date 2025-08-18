import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Random;

public class DailyAdviceServer {
    private final String[] adviceList = {
        "Take smaller bites",
        "Go for the tight jeans. No they do NOT make you look fat.",
        "One word: inappropriate",
        "Just for today, be honest. Tell your boss what you *really* think",
        "You might want to rethink that haircut."
    };

    private final Random random = new Random();
    
    public void go() {

        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
            serverChannel.bind(new InetSocketAddress(5000));

            while (serverChannel.isOpen()) {

                System.out.println("Ready to handle requests");

                SocketChannel clientChannel = serverChannel.accept(); // until a connection occurs it stays here due to it being in blocking mode
                PrintWriter writer = new PrintWriter(Channels.newOutputStream(clientChannel));

                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAdvice() {
        int nextAdvice = random.nextInt(adviceList.length);
        return adviceList[nextAdvice];
    }

    public static void main(String[] args) {
        new DailyAdviceServer().go();
    }
}
