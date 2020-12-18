package blackjack.listeners;

import blackjack.util.TimeTracker;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManagerListener;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * Created by rohitkumar.c on 17/12/20
 **/

public class DocumentSaveListener implements FileDocumentManagerListener {
    @Override
    public void beforeDocumentSaving(@NotNull Document document) {
        System.out.println("Document Saved: " + LocalDateTime.now());
        TimeTracker.trackActivity(LocalDateTime.now());
    }
}
