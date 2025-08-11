package stream.collectors;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsToMap {
    public static void main(String[] args) {
        Map<String, Integer> map1 = Stream.of("Apple", "Banan", "Tomato")
                .collect(Collectors.toMap(
                        name -> name,
                        String::length
                ));
        System.out.println("map1 = " + map1);


        // Duplicate key Apple exception occurs
//        Map<String, Integer> map2 = Stream.of("Apple", "Apple", "Tomato")
//                .collect(Collectors.toMap(
//                        name -> name,
//                        String::length
//                ));
//        System.out.println("map2 = " + map2);

        Map<String, Integer> map3 = Stream.of("Apple", "Apple", "Tomato")
                .collect(Collectors.toMap(
                        name -> name,
                        String::length,
                        (oldValue, newValue) -> oldValue + newValue
                ));
        System.out.println("map3 = " + map3);

        Map<String, Integer> map4 = Stream.of("Apple", "Apple", "Tomato")
                .collect(Collectors.toMap(
                        name -> name,
                        String::length,
                        (oldValue, newValue) -> oldValue + newValue,
                        LinkedHashMap::new
                ));
        System.out.println("map4 = " + map4 + ", class = " + map4.getClass());
    }
}
