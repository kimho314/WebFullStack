package lambda.lambda5.map;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class GenericMap {
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> numbers = new ArrayList<>();
        for (T s : list) {
            R value = function.apply(s);
            numbers.add(value);
        }
        return numbers;
    }
}
