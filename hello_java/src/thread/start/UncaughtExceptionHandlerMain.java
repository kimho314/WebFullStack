package thread.start;

public class UncaughtExceptionHandlerMain {
    static void main() {
        Thread t = new Thread(() -> {
            throw new RuntimeException("Something went wrong");
        });

        // if not setting UncaughtExceptionHandler, thread stops immediately with Exception
        t.setUncaughtExceptionHandler((thread, ex) -> {
            System.out.println("Error in thread: " + thread.getName());
            System.out.println("Exception: " + ex.getMessage());
        });

        t.start();
    }
}
