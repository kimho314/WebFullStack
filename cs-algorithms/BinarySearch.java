import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class BinarySearch {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
        list.sort(Comparator.comparing(integer -> integer));
        System.out.println(list);

        System.out.println(binarySearch(list, 2));
    }

    public static Integer binarySearch(ArrayList<Integer> list, Integer searchItem) {
        if (list.size() == 1 && Objects.equals(list.get(0), searchItem)) {
            return 0;
        }
        if (list.size() == 1 && !list.get(0).equals(searchItem)) {
            return -1;
        }
        if (list.isEmpty()) {
            return -1;
        }

        Integer mid = list.size() / 2;
        if (list.get(mid).equals(searchItem)) {
            return mid;
        } else {
            if (searchItem < list.get(mid)) {
                return binarySearch(new ArrayList<>(list.subList(0, mid)), searchItem);
            } else {
                return binarySearch(new ArrayList<>(list.subList(mid, list.size())), searchItem);
            }
        }
    }
}
