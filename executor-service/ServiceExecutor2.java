import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ServiceExecutor2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // create executor service at fixed 10 thread pool
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                System.out.println("runnable task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Callable<String> callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task's execution";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        // The execute() method is void and doesn't give any possibility to get the result of a task's execution or to check the task's status (is it running):
        executor.execute(runnableTask);

        // submit() submits a Callable or a Runnable task to an ExecutorService and returns a result of type Future
        Future<String> future = executor.submit(callableTask);
        System.out.println(future.get());

        // invokeAny() assigns a collection of tasks to an ExecutorService, causing each to run, and returns the result of a successful execution of one task (if there was a successful execution):
        String result = executor.invokeAny(callableTasks);
        System.out.println(result);

        // invokeAll() assigns a collection of tasks to an ExecutorService, causing each to run, and returns the result of all task executions in the form of a list of objects of type Future:
        List<Future<String>> futures = executor.invokeAll(callableTasks);
        System.out.println(futures.stream().map(it -> {
            try {
                return it.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.joining(", ")));
    }
}
