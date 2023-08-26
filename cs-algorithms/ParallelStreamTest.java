import java.util.stream.LongStream;

public class ParallelStreamTest {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // or System.nanoTime();

        // Code whose execution time you want to measure
        streamFunc();

        long endTime = System.currentTimeMillis(); // or System.nanoTime();
        long elapsedTime = endTime - startTime;

        System.out.println("Elapsed Time: " + elapsedTime + " ms");

        startTime = System.currentTimeMillis(); // or System.nanoTime();

        // Code whose execution time you want to measure
        parallelStreamFunc();

        endTime = System.currentTimeMillis(); // or System.nanoTime();
        elapsedTime = endTime - startTime;

        System.out.println("Elapsed Time: " + elapsedTime + " ms");
    }

    private static void streamFunc() {
        LongStream.range(0, Long.MAX_VALUE)
                .filter(it -> it % 2 == 0)
                .map(it -> it * 2);
    }

    private static void parallelStreamFunc() {
        LongStream.range(0, Long.MAX_VALUE).parallel()
                .filter(it -> it % 2 == 0)
                .map(it -> it * 2);
    }
}
