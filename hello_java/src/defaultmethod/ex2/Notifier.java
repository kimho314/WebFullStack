package defaultmethod.ex2;

import java.time.LocalDateTime;

public interface Notifier {
    void notify(String message);

    default void scheduleNotification(String message, LocalDateTime scheduleTime) {
        System.out.println("[DEFAULT SCHEDULING] message = " + message + ", time = " + scheduleTime);
    }
}
