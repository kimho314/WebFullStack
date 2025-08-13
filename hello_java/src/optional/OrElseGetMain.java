package optional;

import java.util.Optional;
import java.util.Random;

public class OrElseGetMain {
    public static void main(String[] args) {
        Optional<Integer> optValue = Optional.of(100);
        Optional<Integer> optEmpty = Optional.empty();

        Integer i1 = optValue.orElse(10 + 20); // 10+20 계산 후 버림
        Integer i2 = optEmpty.orElse(10 + 20); // // 10+20 계산 후 사용
        System.out.println("i1 = " + i1);
        System.out.println("i2 = " + i2);

        System.out.println("=== orElse ====");
        Integer value1 = optValue.orElse(createData());
        Integer value2 = optEmpty.orElse(createData());
        System.out.println("value1 = " + value1);
        System.out.println("value2 = " + value2);

        System.out.println("=== orElseGet ===");
        Integer value3 = optValue.orElseGet(OrElseGetMain::createData);
        Integer value4 = optEmpty.orElseGet(OrElseGetMain::createData);
        System.out.println("value3 = " + value3);
        System.out.println("value4 = " + value4);
    }

    private static int createData() {
        System.out.println("create data");
        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int createValue = new Random().nextInt(100);
        System.out.println("createValue = " + createValue);
        return createValue;
    }
}
