import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();

        for (int i = 0; i < 10; i++) {
            map.put("foo" + i, "bar" + i);
        }
        map.put("foo5", "re-insert bar5");
        map.put("foo11", "bar11");

        map.entrySet().forEach(entry -> System.out.println(entry.toString()));

        System.out.println("===================");

        LinkedHashMap<String, String> map2 = new LinkedHashMap<>(1000, 0.75f, true);
        for (int i = 0; i < 10; i++) {
            map2.put("foo" + i, "bar" + i);
        }
        map2.put("foo5", "re-insert bar5");
        map2.put("foo11", "bar11");
        map2.get("foo3");
        map2.entrySet().forEach(entry -> System.out.println(entry.toString()));

        System.out.println("===================");

        LinkedHashMap<String, String> lhm = new LinkedHashMap<>(1000, 0.75f, true) {

            private final int MAX = 10;

            protected boolean removeEldestEntry(java.util.Map.Entry<String, String> eldest) {
                return size() >= MAX;
            }
        };

        for (int i = 0; i < 10; i++) {
            lhm.put("foo" + i, "bar" + i);
        }

        lhm.put("foo5", "re-insert bar5");
        lhm.put("foo4", "re-insert bar4");
        lhm.put("foo12", "bar12");
        lhm.put("foo13", "bar13");
        lhm.put("foo14", "bar14");
        lhm.put("foo15", "bar15");
        lhm.put("foo5", "re-insert bar5");

        for (Map.Entry<String, String> string : lhm.entrySet()) {
            System.out.println(string);
        }
    }
}
