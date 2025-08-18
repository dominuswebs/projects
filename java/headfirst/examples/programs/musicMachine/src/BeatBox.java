package musicMachine.src;

import javax.sound.midi.*;

public class BeatBox {

    public void play() {

        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            System.out.println("Successfully got a sequencer.");
        } catch (MidiUnavailableException e) {
            System.out.println("Oh Oh. Sequencer failed");
        }
    }

    public static void main(String[] args) {
        BeatBox bb = new BeatBox();

        bb.play();
    }
}