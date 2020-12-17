package blackjack.player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class SoundClipUtil {
    public static Clip openClip(AudioInputStream audioInputStream ) throws IOException, LineUnavailableException {
        Clip clip = AudioSystem.getClip();
        clip.open( audioInputStream );
        clip.addLineListener( event -> {
            if ( event.getType() == LineEvent.Type.STOP ) {
                clip.close();
            }
        } );

        return clip;
    }
}
