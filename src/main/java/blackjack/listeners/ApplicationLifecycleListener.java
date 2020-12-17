package blackjack.listeners;

import blackjack.util.TimeTracker;
import com.intellij.ide.AppLifecycleListener;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rohitkumar.c on 17/12/20
 **/

public class ApplicationLifecycleListener implements AppLifecycleListener {
    @Override
    public void appFrameCreated(@NotNull List<String> commandLineArgs) {
        TimeTracker.trackActivity(LocalDateTime.now());
    }
}
