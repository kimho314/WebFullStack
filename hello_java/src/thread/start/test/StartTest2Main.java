package thread.start.test;

import util.MyLogger;

public class StartTest2Main {
    static void main() {
        new Thread(new CounterRunnable()).start();
    }

    static class CounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 5; i++) {
                MyLogger.log(i);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
