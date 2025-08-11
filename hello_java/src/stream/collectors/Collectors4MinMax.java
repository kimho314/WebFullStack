package stream.collectors;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collectors4MinMax {
    public static void main(String[] args) {
        // downstream collector에서 유용하게 사용
        Integer max1 = Stream.of(1, 2, 3)
                .collect(Collectors.maxBy(
                        (n1, n2) -> n1 - n2
                ))
                .orElse(-1);
        System.out.println("max1 = " + max1);

        Integer max2 = Stream.of(1, 2, 3)
                .max((n1, n2) -> n1 - n2)
                .orElse(-1);
        System.out.println("max2 = " + max2);

        Integer max3 = Stream.of(1, 2, 3)
                .max(Integer::compareTo)
                .orElse(-1);
        System.out.println("max3 = " + max3);

        int max4 = IntStream.of(1, 2, 3)
                .max()
                .orElse(-1);
        System.out.println("max4 = " + max4);
    }
}
