import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * thread의 갯수가 1개이기 때문에 요청한 작업이 순서대로 처리된다
 */
public class SingleThreadExecutor {
    public static void main(String[] args) throws InterruptedException {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Job1 " + threadName);
            });
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Job2 " + threadName);
            });
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Job3 " + threadName);
            });
            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Job4 " + threadName);
            });

            executor.shutdown();
            executor.awaitTermination(20, TimeUnit.SECONDS);
            System.out.println("end");
        }
}
