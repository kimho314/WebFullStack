package lambda.lambda5.map;

import java.util.List;

import static lambda.lambda5.map.StringToIntegerMapper.map;

public class MapMainV3 {
    public static void main(String[] args) {
        List<String> list = List.of("1", "12", "123", "1234");

        List<Integer> numbers = map(list, (value) -> Integer.parseInt(value));
        System.out.println("numbers = " + numbers);

        List<Integer> lengths = map(list, (value) -> value.length());
        System.out.println("lengths = " + lengths);
    }

}
