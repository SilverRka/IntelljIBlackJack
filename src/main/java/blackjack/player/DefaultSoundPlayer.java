package blackjack.player;

import com.intellij.openapi.diagnostic.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public final class DefaultSoundPlayer {

    private static final Logger LOGGER = Logger.getInstance( DefaultSoundPlayer.class );

    private final String filePath;

    private Clip clip;

    private DefaultSoundPlayer(String filePath ) {
        this.filePath = filePath;
    }

    public static DefaultSoundPlayer ofFile( String filePath ) {
        return new DefaultSoundPlayer( filePath );
    }

    public void play() {
        try (AudioInputStream soundStream = AudioSystem.getAudioInputStream(DefaultSoundPlayer.class.getClassLoader().getResourceAsStream(filePath)) ) {
            clip = SoundClipUtil.openClip( soundStream );
            clip.start();
        } catch ( IOException | LineUnavailableException | UnsupportedAudioFileException e ) {
            LOGGER.warn( e.getMessage(), e );
        }
    }

    public void playAndWait() {
        play();
        try {
            Thread.sleep( clip.getMicrosecondLength() / 1000 );
        } catch ( InterruptedException e ) {
            LOGGER.warn( e.getMessage(), e );
        }
    }

    public void stop() {
        if ( clip == null ) {
            LOGGER.warn( "Unable to stop clip because it was never started" );
            return;
        }
        clip.stop();
    }
}
