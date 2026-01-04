package thread.collections;

import java.util.concurrent.CountDownLatch;

public class CountDownLatch1 {
    static void main() throws InterruptedException {
        // We are waiting for 3 services
        CountDownLatch latch = new CountDownLatch(3);

        // Start 3 "Service" threads
        new Thread(new Service("Database", 2000, latch)).start();
        new Thread(new Service("Cache", 1000, latch)).start();
        new Thread(new Service("Messaging", 3000, latch)).start();

        System.out.println("Main thread waiting for services...");

        // Main thread blocks here
        latch.await();

        System.out.println("All services are up. Starting application!");
    }

    static class Service implements Runnable {
        private final String name;
        private final int delay;
        private final CountDownLatch latch;

        public Service(String name, int delay, CountDownLatch latch) {
            this.name = name;
            this.delay = delay;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                System.out.println(name + " is initialized.");
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            finally {
                latch.countDown(); // Decrement the count
            }
        }
    }
}
