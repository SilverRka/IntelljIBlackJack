package blackjack.dialogs;

import blackjack.util.TimeTracker;
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
        long timeNumber = TimeTracker.maxContinuousThreshold >= 60 ? TimeTracker.maxContinuousThreshold/60 : TimeTracker.maxContinuousThreshold;
        String timeUnit = TimeTracker.maxContinuousThreshold >= 60 ? "minute" : "second";
        JLabel label = new JLabel("You have worked more than "+ timeNumber + " " + (timeNumber > 1 ? timeUnit + "s" : timeUnit) + " continuously. Do you want to take break for some time?");
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label, BorderLayout.CENTER);

        return dialogPanel;
    }
}

