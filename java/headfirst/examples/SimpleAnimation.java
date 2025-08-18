import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;


public class SimpleAnimation {
    private int xPos = 70;
    private int yPos = 70;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);

        for (int i = 0; i < 130; i++) {
            xPos++;
            yPos++;

            drawPanel.repaint();

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class MyDrawPanel extends JPanel {
        @Override // the paint component defined in JComponent that JPanel then extends. This needs to be overriden because the JPanel definition creates an empty JPanel
        public void paintComponent(Graphics g) {
            super.paintComponent(g); // Clears the panel and performs default painting
            g.setColor(Color.green);
            g.fillOval(xPos, yPos, 40, 40);
        }
    }
}
