import javax.sound.midi.*;
import static javax.sound.midi.ShortMessage.*;

public class SoundPlayer {

    public static void main(String[] args) {

        SoundPlayer app = new SoundPlayer();

        app.play();
    }

    public void play() {
        try {

            Sequencer player = MidiSystem.getSequencer();
            player.open();

            // Add a meta event listener to detect the end of the sequence
            player.addMetaEventListener( meta-> { // we are using a lambda here because the addMetaEventListener takes a type MetaEventListener which is a SAM
                    if (meta.getType() == 47) {
                        System.out.println("All tracks have finished playing!");
                        player.close();
                    }
                }
            );

            // the above can also be written like this, an anonymous inner class
            /*
            player.addMetaEventListener(new MetaEventListener() {
                @Override
                public void meta(MetaMessage meta) {
                    if (meta.getType() == 47) {
                        System.out.println("All tracks have finished playing!");
                        player.close();
                    }
                }
            });
            */
            
            Sequence seq = new Sequence(Sequence.PPQ, 4); // this makes 8 ticks = 1 second

            Track track = seq.createTrack();

            ShortMessage inst = new ShortMessage();
            
            inst.setMessage(192, 1, 2, 0); // changing the instrument from default (piano) to electric guitar
            MidiEvent changeInstr = new MidiEvent(inst, 1);
            track.add(changeInstr);

            /*
             * 
             *  NOTE ON NOTES
             * 
             * Each instrument sustains notes differently. Setting noteOff to something like 10 seconds doesnt mean the note will play for 10 seconds
             * 
             */
            
            ShortMessage msg1 = new ShortMessage();
            msg1.setMessage(NOTE_ON, 1, 44, 100); // NOTE_ON is a ShortMessage constant 
            MidiEvent noteOn = new MidiEvent(msg1, 1);
            track.add(noteOn);
            
            ShortMessage msg2 = new ShortMessage();
            msg2.setMessage(NOTE_OFF, 1, 44, 100); // NOTE_OFF is a ShortMessage constant
        
            MidiEvent noteOff = new MidiEvent(msg2, 9);
            track.add(noteOff);

            ShortMessage msg3 = new ShortMessage();
            msg3.setMessage(NOTE_ON, 1, 50, 100);
            noteOn = new MidiEvent(msg3, 9);
            track.add(noteOn);

            ShortMessage msg4 = new ShortMessage();
            msg4.setMessage(NOTE_OFF, 1, 50, 100);
            noteOff = new MidiEvent(msg4, 25);
            track.add(noteOff);

            player.setSequence(seq);

            player.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
