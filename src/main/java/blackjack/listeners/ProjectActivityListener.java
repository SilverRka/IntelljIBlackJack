package blackjack.listeners;

import blackjack.util.TimeTracker;
import com.intellij.task.ProjectTaskListener;
import com.intellij.task.ProjectTaskManager;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * Created by rohitkumar.c on 17/12/20
 **/

public class ProjectActivityListener implements ProjectTaskListener {
    @Override
    public void finished(ProjectTaskManager.@NotNull Result result) {
        TimeTracker.trackActivity(LocalDateTime.now());
    }
}
