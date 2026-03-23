package thread.collections;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrier1 {
    static void main() {
        CyclicBarrier barrier = new CyclicBarrier(3);

        Runnable task = () -> {
            IO.println(Thread.currentThread().getName() + " reached barrier");
            try {
                barrier.await(); // wait for others
            }
            catch (Exception e) {
            }

            IO.println(Thread.currentThread().getName() + " passed barrier");
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}
