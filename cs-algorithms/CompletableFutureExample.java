import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {

    public static void main(String[] args) {
//        example1();
        System.out.println("================");
        example2();
        System.out.println("================");
//        example3();
    }

    private static void example3() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");

        // Using thenAccept() method
        CompletableFuture<Void> future2 = future1.thenAccept(result -> {
            System.out.println(Thread.currentThread().getName() + ": " + result);
        });

        // Using thenAcceptAsync() method
        CompletableFuture<Void> future3 = future1.thenAcceptAsync(result -> {
            System.out.println(Thread.currentThread().getName() + ": " + result);
        });

        // Wait for both futures to complete
        CompletableFuture.allOf(future2, future3).join();
    }

    private static void example2() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            // Do some work
            System.out.println("Function 1 started.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Function 1 finished.");
            return 10;
        });

        CompletableFuture<Integer> future2 = future1.thenApplyAsync(result -> {
            System.out.println(Thread.currentThread().getName());
            // Do some work with the result of future1
            System.out.println("Function 2 started with result " + result + ".");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Function 2 finished.");
            return result * 2;
        });

        CompletableFuture<Void> future3 = future2.thenAcceptAsync(result -> {
            System.out.println(Thread.currentThread().getName());
            // Do some work with the result of future2
            System.out.println("Function 3 started with result " + result + ".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Function 3 finished.");
        });

        // Wait for all futures to finish
        future3.join();

        // Done
        System.out.println("All functions have finished.");
    }

    private static void example1() {
        // Call function1 asynchronously
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(CompletableFutureExample::function1);

        // Call function2 asynchronously
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(CompletableFutureExample::function2);

        // Wait for both futures to finish
        CompletableFuture.allOf(future1, future2).join();

        // Done
        System.out.println("All functions have finished.");
    }

    public static void function1() {
        // Do some work
        System.out.println("Function 1 started.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Function 1 finished.");
    }

    public static void function2() {
        // Do some work
        System.out.println("Function 2 started.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Function 2 finished.");
    }
}
