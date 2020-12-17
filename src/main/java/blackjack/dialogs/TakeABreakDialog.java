package blackjack.dialogs;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by rohitkumar.c on 17/12/20
 **/

public class TakeABreakDialog extends DialogWrapper {
    private static final String TITLE = "Take a Break";

    public TakeABreakDialog() {
        super(true); // use current window as parent
        init();
        setTitle(TITLE);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("You have worked more than 1 min continuously. Please take a brak");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}

