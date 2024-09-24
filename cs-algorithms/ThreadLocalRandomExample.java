import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ThreadLocalRandomExample {
    private int high;
    private int low;

    public ThreadLocalRandomExample(int low, int high) {
        this.low = low;
        this.high = high;
    }

    public void generateRandomJava7() {
        System.out.println("# Java 7 style #");
        for (int i = 0; i < 100; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt(low, high));
        }
    }

    public void generateRandomJava8() {
        System.out.println("# Java 8 style #");
        IntStream intStream = ThreadLocalRandom.current().ints(100, low, high);
        intStream.forEach(System.out::println);
    }

    public static void main(String[] args) {
        ThreadLocalRandomExample example = new ThreadLocalRandomExample(100, 1000);
        example.generateRandomJava7();
        example.generateRandomJava8();
    }
}
