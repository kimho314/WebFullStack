import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
//        long startTime = System.currentTimeMillis();
//        int sum = 0;
//        for (int i = 0; i < 30; i++) {
//            sum += getRandomNum();
//        }
//        System.out.println("sum : " + sum);
//        long endTime = System.currentTimeMillis();
//        long elapsedTime = endTime - startTime; // 30167 milli
//        System.out.println("elapsedTime : " + elapsedTime);


        long startTime = System.currentTimeMillis();
        List<CompletableFuture<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(CompletableFuture.supplyAsync(CompletableFutureExample::getRandomNum));
        }
        Integer sum = CompletableFuture.allOf(list.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v -> list.stream().mapToInt(CompletableFuture::join).sum())
                .join();
        System.out.println("sum : " + sum);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println("elapsedTime : " + elapsedTime); // 5034 milli
    }

    /**
     * generate random integer between 0 and 100
     */
    public static Integer getRandomNum() {
        int num = (int) (Math.random() * 100);
        System.out.println(Thread.currentThread().getName() + " num : " + num);
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return num;
    }
}
