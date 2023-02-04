import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.offer(3);
        queue.offer(5);
        queue.forEach(System.out::println);
        System.out.println("===============");
        for (int i = 0; i < 3; i++) {
            System.out.println(queue.poll());
        }
    }
}
