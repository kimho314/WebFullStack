import java.util.ArrayList;
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

        System.out.println("=================");
        SimpleQueue<Integer> simpleQueue = new SimpleQueue<>();
        simpleQueue.enqueue(1);
        simpleQueue.enqueue(2);
        simpleQueue.enqueue(3);
        System.out.println("size : " + simpleQueue.size());
        //simpleQueue.elems.forEach(System.out::println);
        while (simpleQueue.size() > 0) {
            System.out.println(simpleQueue.dequeue());
        }
    }

    public static class SimpleQueue<E> {
        private ArrayList<E> elems;

        public SimpleQueue() {
            elems = new ArrayList<>();
        }

        public Integer size() {
            return this.elems.size();
        }

        public boolean enqueue(E elem) {
            return elems.add(elem);
        }

        public E dequeue() {
            if (elems.isEmpty()) {
                return null;
            }
            return elems.remove(0);
        }

        public E peek() {
            if (elems.isEmpty()) {
                return null;
            }
            return elems.get(0);
        }
    }
}
