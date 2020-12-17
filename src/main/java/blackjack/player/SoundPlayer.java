package blackjack.player;

import com.intellij.openapi.diagnostic.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public final class SoundPlayer {

    private static final Logger LOGGER = Logger.getInstance( SoundPlayer.class );

    private final Path soundFilePath;

    private AudioDevice audioDevice;

    private AdvancedPlayer player;

    private SoundPlayer(Path soundFilePath ) {
        this.soundFilePath = soundFilePath;
    }

    public static SoundPlayer ofFile( Path soundFilePath ) {
        return new SoundPlayer( soundFilePath );
    }

    public void play() {
        initPlayer( l -> new Thread( l ).start() );
    }

    public void stop() {
        if ( player == null || audioDevice == null ) {
            LOGGER.warn( "Unable to stop player because player has not started!" );
            return;
        }

        if ( audioDevice.isOpen() ) player.close();
    }

    public void playAndWait() {
        initPlayer( Runnable::run );
    }

    private void initPlayer( Consumer<Runnable> runnableConsumer ) {
        try {
            InputStream soundStream = new BufferedInputStream( Files.newInputStream( soundFilePath ));
            playSound( runnableConsumer, soundStream );
        } catch ( IOException e ) {
            LOGGER.warn( e.getMessage(), e );
        }
    }

    private void playSound(Consumer<Runnable> runnableConsumer, InputStream soundStream ) throws IOException {
        try {
            audioDevice = FactoryRegistry.systemRegistry().createAudioDevice();
            player = new AdvancedPlayer( soundStream, audioDevice );
            player.setPlayBackListener( buildPlaybackListener( soundStream ) );
            runnableConsumer.accept( this::invokePlay );
        } catch ( Exception e ) {
            LOGGER.warn( e.getMessage(), e );
            soundStream.close();
        }
    }

    @NotNull
    private PlaybackListener buildPlaybackListener( InputStream soundStream ) {
        return new PlaybackListener() {
            @Override
            public void playbackFinished( PlaybackEvent evt ) {
                try {
                    soundStream.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void invokePlay() {
        try {
            player.play();
        } catch ( JavaLayerException e ) {
            LOGGER.warn( "Cannot play sound '" + soundFilePath + "': " + e.getMessage(), e );
        }
    }

}
