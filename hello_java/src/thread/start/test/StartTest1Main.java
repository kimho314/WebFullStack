package thread.start.test;

public class StartTest1Main {
    static void main() {
        CounterThread counterThread = new CounterThread();
        counterThread.start();
    }
}
