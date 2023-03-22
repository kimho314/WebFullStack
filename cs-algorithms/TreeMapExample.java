import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("B", 2);
        treeMap.put("A", 1);
        treeMap.put("D", 4);
        treeMap.put("C", 3);
        treeMap.put("E", 8);
        treeMap.put("F", 6);
        treeMap.put("G", 7);
        treeMap.put("L", 5);
        treeMap.put("M", 9);
        treeMap.forEach((k, v) -> System.out.println("k = " + k + ", v = " + v));
        System.out.println("===================");
        NavigableMap<String, Integer> stringIntegerNavigableMap = treeMap.descendingMap(); // descending by key
        stringIntegerNavigableMap.forEach((k, v) -> System.out.println("k = " + k + ", v = " + v));
        System.out.println("===================");
        NavigableSet<String> strings = treeMap.descendingKeySet();
        strings.forEach(System.out::println);
        System.out.println("===================");
        System.out.println("first key = " + treeMap.firstKey() + ", last key = " + treeMap.lastKey());
        System.out.println("===================");
        Entry<String, Integer> firstEntry = treeMap.pollFirstEntry();
        System.out.println("firstEntry = " + firstEntry);
        treeMap.forEach((k, v) -> System.out.println("k = " + k + ", v = " + v));
        System.out.println("===================");
        System.out.println("higherKey");
        System.out.println(treeMap.higherKey("B"));
        System.out.println("lowerKey");
        System.out.println(treeMap.lowerKey("E"));
        System.out.println("===================");
        System.out.println("tailMap");
        System.out.println(treeMap.tailMap("D"));
    }
}
