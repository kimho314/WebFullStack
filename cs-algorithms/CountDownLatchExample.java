import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 5;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(countDownLatch)))
                .limit(threadCount)
                .toList();

        System.out.println("[" + Thread.currentThread().getName() + "]" + "Start Multi Threads");
        workers.forEach(Thread::start);
        countDownLatch.await();
        System.out.println("[" + Thread.currentThread().getName() + "]" + "waiting for some workers to be finished");
        System.out.println("[" + Thread.currentThread().getName() + "]" + "Finished");
    }

    static class Worker implements Runnable {
        private CountDownLatch countDownLatch;

        public Worker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("[" + Thread.currentThread().getName() + "]" + "execute task");
            countDownLatch.countDown();
        }
    }

}
