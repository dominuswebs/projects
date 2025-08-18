package musicArt.src;

import javax.sound.midi.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;
import static javax.sound.midi.ShortMessage.*;

/*
 * In terminal navigate to the root folder of the project (musicArt) then run:
 * javac -d bin src/MusicArt.java 
 * java -cp bin musicArt.src.MusicArt 
 */

public class MusicArt {

    private MyDrawPanel panel;
    private MyDrawPanel2 panel2;
    private Random random = new Random();

    public static void main(String[] args) {
        MusicArt mini = new MusicArt();
        mini.go();
    }

    public void setUpGui() {
        JFrame frame = new JFrame("My First Music Video");
        panel = new MyDrawPanel();
        panel2 = new MyDrawPanel2();
        // this creates 2 areas for each panel. without this line panel2 would override panel 
        // it simply places components in a grid, filling rows and columns in a left-to-right, top-to-bottom order.
        frame.getContentPane().setLayout(new GridLayout(1, 2)); 
        
        frame.getContentPane().add(panel);
        frame.getContentPane().add(panel2);
        frame.setBounds(30, 30 ,500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void go() {

        setUpGui();

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            // The event passed in is a ShortMessage whose first data byte indicates the controller number and whose second data byte is the value to which the controller was set.
            // controller events only. NOTE_ON and NOTE_OFF are not controller events. 
            // panel is an object that has an implementation of controlChange (SAM) that is called when the event listener is triggered
            
            
            sequencer.addControllerEventListener(panel2, new int[]{126});
            sequencer.addControllerEventListener(panel, new int[]{127});
            


            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int note;

            for (int i = 0; i < 60; i += 4) {
                note = random.nextInt(50) + 1;
                track.add(makeEvent(NOTE_ON, 1, note , 100, i));
                track.add(makeEvent(CONTROL_CHANGE, 1, 127, 0, i));
                track.add(makeEvent(NOTE_OFF, 1, note , 100, i + 2));
                track.add(makeEvent(CONTROL_CHANGE, 1, 126, 0, i + 2));
            }

            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(120);
            sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MidiEvent makeEvent(int cmd, int chnl, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage msg = new ShortMessage();
            msg.setMessage(cmd, chnl, one, two);
            event = new MidiEvent(msg, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    // inner class
    class MyDrawPanel extends JPanel implements ControllerEventListener { // ControllerEventListener is a functional interface with a SAM (single abstract method) called controlChange

        private boolean msg = false;

        MyDrawPanel() {
            System.out.println("new draw panel created");
        }

        public void controlChange(ShortMessage event) { // override from ControllerEventListener
            System.out.println(event.getData1());
            msg = true;
            repaint(); // triggers a repaint of this object, calling the paintComponent method below
        }

        public void paintComponent(Graphics g) {
            if(msg) {
                int r = random.nextInt(250);
                int gr = random.nextInt(250);
                int b = random.nextInt(250);

                g.setColor(new Color(r, gr, b));

                int height = random.nextInt(120) + 10;
                int width = random.nextInt(120) + 10;

                int xPos = random.nextInt(40) + 10;
                int yPos = random.nextInt(40) + 10;
                g.fillRect(xPos, yPos, width, height);
                msg = false;
            }
        }

    }

        // inner class
    class MyDrawPanel2 extends JPanel implements ControllerEventListener { // ControllerEventListener is a functional interface with a SAM (single abstract method) called controlChange

        private boolean msg = false;

        MyDrawPanel2() {
            System.out.println("new draw panel2 created");
        }

        public void controlChange(ShortMessage event) { // override from ControllerEventListener
            
            System.out.println(event.getData1());
            msg = true;
            repaint(); // triggers a repaint of this object, calling the paintComponent method below
        }

        public void paintComponent(Graphics g) {
            if(msg) {
                int r = random.nextInt(250);
                int gr = random.nextInt(250);
                int b = random.nextInt(250);

                g.setColor(new Color(r, gr, b));

                int height = random.nextInt(120) + 10;
                int width = random.nextInt(120) + 10;

                int xPos = random.nextInt(40) + 100;
                int yPos = random.nextInt(40) + 100;
                g.fillRect(xPos, yPos, width, height);
                msg = false;
            }
        }

    }
}


