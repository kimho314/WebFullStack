package thread.start.test;

import util.MyLogger;

public class StartTest4Main {
    static void main() {
        while (true) {
            new Thread(() -> {
                try {
                    MyLogger.log("A");
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-A").start();

            new Thread(() -> {
                try {
                    MyLogger.log("B");
                    Thread.sleep(500);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-B").start();
        }

    }

}
