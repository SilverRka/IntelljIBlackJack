package blackjack.player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.io.InputStream;

public class SoundClipUtil {
    public static Clip openClip(InputStream inputStream ) throws IOException,
            UnsupportedAudioFileException, LineUnavailableException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream( inputStream );
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
