import java.util.concurrent.*;

public class ServiceExecutor3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.println("callableTask");
            return "Task's execution";
        };

//        Future<String> resultFuture = executorService.schedule(callableTask, 1, TimeUnit.SECONDS);
//        System.out.println(resultFuture.get());

        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("command");
        };
        executorService.scheduleAtFixedRate(task, 100, 100, TimeUnit.MILLISECONDS);
    }
}
