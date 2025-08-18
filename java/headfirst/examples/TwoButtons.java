import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TwoButtons {
    private JFrame frame;
    private JLabel label;
    private int counter = 0;

    public static void main(String[] args) {
        TwoButtons gui = new TwoButtons();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change label");
        
        // this uses the inner class defined below. Can be replaced by a lambda
        // labelButton.addActionListener(new LabelListener());
        labelButton.addActionListener(event -> {
                label.setText("Updated with a lambda!" + counter);
                counter++;
            }
        );

        JButton colorButton = new JButton("Change circle");
        
        // this uses the inner class defined below. Can be replaced by a lambda
        // colorButton.addActionListener(new ColorListener());
        colorButton.addActionListener(event -> frame.repaint());

        label = new JLabel("I'm a label");

        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);

        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    /* 
     * These 2 inner classes can be replaced with lambdas
    class LabelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            label.setText("Ouch!");
        }
    }

    class ColorListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.repaint();
        }
    }
    */
}
