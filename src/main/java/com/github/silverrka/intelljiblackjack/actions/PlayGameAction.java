package com.github.silverrka.intelljiblackjack.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class PlayGameAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        int amount = 1000;
        BlackJack blackJack
                = new BlackJack(amount);

    }

    @Override
    public void update(AnActionEvent e) {

        // Using the event, evaluate the context, and enable or disable the action.
    }
}
