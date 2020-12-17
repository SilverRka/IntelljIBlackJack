package blackjack.listeners;

import blackjack.util.TimeTracker;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * Created by rohitkumar.c on 17/12/20
 **/

public class FileEditorEventListener implements FileEditorManagerListener {
    @Override
    public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        TimeTracker.trackActivity(LocalDateTime.now());
    }

    @Override
    public void fileClosed(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
        TimeTracker.trackActivity(LocalDateTime.now());
    }
}
