package thread.start.test;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceConditionMain {
    //    private static int counter = 0;
    private static AtomicInteger counter = new AtomicInteger(0);


    static void main() throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
//                counter++;
                counter.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
//                counter++;
                counter.incrementAndGet();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        IO.println(counter);
    }
}
