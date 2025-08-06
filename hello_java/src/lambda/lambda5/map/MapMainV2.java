package lambda.lambda5.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class MapMainV2 {
    public static void main(String[] args) {
        List<String> list = List.of("1", "12", "123", "1234");

        List<Integer> numbers = map(list, (value) -> Integer.parseInt(value));
        System.out.println("numbers = " + numbers);

        List<Integer> lengths = map(list, (value) -> value.length());
        System.out.println("lengths = " + lengths);
    }

    private static List<Integer> map(List<String> list, Function<String, Integer> function) {
        List<Integer> numbers = new ArrayList<>();
        for (String s : list) {
            Integer value = function.apply(s);
            numbers.add(value);
        }
        return numbers;
    }

}
