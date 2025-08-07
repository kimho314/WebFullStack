package lambda.lambda5.mystream;

import lambda.lambda5.filter.GenericFilter;
import lambda.lambda5.map.GenericMap;

import java.util.ArrayList;
import java.util.List;

public class Ex1_Number {
    public static void main(String[] args) {
        // 짝수만 남기고, 남은 값의 2배를 반환
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> directResult = direct(numbers);
        System.out.println("directResult = " + directResult);
        List<Integer> lambdaResult = lambda(numbers);
        System.out.println("lambdaResult = " + lambdaResult);
    }

    private static List<Integer> lambda(List<Integer> numbers) {
        List<Integer> filtered = GenericFilter.filter(numbers, (value) -> value % 2 == 0);
        List<Integer> mapped = GenericMap.map(filtered, (value) -> value * 2);
        return mapped;
    }

    private static List<Integer> direct(List<Integer> numbers) {
        List<Integer> result = new ArrayList<>();
        for (Integer elem : numbers) {
            if (elem % 2 == 0) {
                result.add(elem * 2);
            }
        }
        return result;
    }
}
