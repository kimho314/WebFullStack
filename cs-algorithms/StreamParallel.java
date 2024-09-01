import java.util.Arrays;
import java.util.List;

public class StreamParallel {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        long start = System.currentTimeMillis();
//        numbers.forEach(number -> {
//            try {
//                Thread.sleep(3000);
//                System.out.println(number + ": " + Thread.currentThread().getName());
//            }
//            catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
        numbers.parallelStream().forEach(number -> {
            try {
                Thread.sleep(3000);
                System.out.println(number + ": " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long duration = (System.currentTimeMillis() - start);
        double seconds = duration / 1000.0;
        System.out.printf("Done in %.2f sec\n", seconds);
    }
}