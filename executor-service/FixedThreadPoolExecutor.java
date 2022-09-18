import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedThreadPoolExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

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

        // 더이상 ExecutorService에 Task를 추가할 수 없습니다.
        // 작업이 모두 완료되면 쓰레드풀을 종료시킵니다.
//        executor.shutdown();

        // shutdown() 호출 전에 등록된 Task 중에 아직 완료되지 않은 Task가 있을 수 있습니다.
        // Timeout을 20초 설정하고 완료되기를 기다립니다.
        // 20초 전에 완료되면 true를 리턴하며, 20초가 지나도 완료되지 않으면 false를 리턴합니다.
        if (executor.awaitTermination(2, TimeUnit.SECONDS)) { // gracefully shutdown
            System.out.println(LocalTime.now() + " All jobs are terminated");
        } else {
            System.out.println(LocalTime.now() + " some jobs are not terminated");

            // 모든 Task를 강제 종료합니다.
            executor.shutdownNow();
        }

        System.out.println("end");
    }
}
