package blackjack.util;

import blackjack.Tester;
import blackjack.dialogs.TakeABreakDialog;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by rohitkumar.c on 17/12/20
 **/

public class TimeTracker {
    private static long firstActivityAt = 0;
    private static long lastActivityAt = 0;
    public static final long continuousThreshold = 10;
    public static final long maxContinuousThreshold = 120;

    public static void trackActivity(LocalDateTime time) {
        ZoneId zoneId = ZoneId.systemDefault();
        long now = time.atZone(zoneId).toEpochSecond();

        boolean continuous = ((now - lastActivityAt) < continuousThreshold);

        if (continuous) {
            lastActivityAt = now;

            if (now - firstActivityAt > maxContinuousThreshold) {
                firstActivityAt = now;
                if (new TakeABreakDialog().showAndGet()) {
                    Tester.openMenu();
                }
            }
        } else {
            System.out.println("start time reset to: " + time);
            firstActivityAt = now;
            lastActivityAt = now;
        }
    }
}
