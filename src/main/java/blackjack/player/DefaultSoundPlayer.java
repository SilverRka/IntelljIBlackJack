package blackjack.player;

import com.intellij.openapi.diagnostic.Logger;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class DefaultSoundPlayer {

    private static final Logger LOGGER = Logger.getInstance( DefaultSoundPlayer.class );

    private final Path filePath;

    private Clip clip;

    private DefaultSoundPlayer(Path filePath ) {
        this.filePath = filePath;
    }

    public static DefaultSoundPlayer ofFile( Path filePath ) {
        return new DefaultSoundPlayer( filePath );
    }

    public void play() {
        try ( InputStream soundStream = new BufferedInputStream(Files.newInputStream( filePath )) ) {
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
