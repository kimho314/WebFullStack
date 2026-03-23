package thread.collections;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch2 {
    static void main() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Runnable worker = () -> {
            IO.println(latch.getCount() + " tasks remaining");
            IO.println(Thread.currentThread().getName() + " done");
            latch.countDown();
        };

        new Thread(worker).start();
        new Thread(worker).start();
        new Thread(worker).start();
        IO.println("waiting...");
        latch.await(); // wait until count reaches 0
        IO.println("waiting done...");
        IO.println("All tasks completed");
    }
}
