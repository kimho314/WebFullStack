package stream.basic;

import java.util.List;

public class MutableMain {
    public static void main(String[] args) {
        List<Integer> originList = List.of(1, 2, 3, 4);
        System.out.println("originList: " + originList);

        List<Integer> filtered = originList.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println("filtered: " + filtered);
        System.out.println("originList: " + originList);
    }
}
