import java.util.ArrayList;
import java.util.Objects;

public class BinarySearch {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 100));
        }
//        System.out.println(list);
//        list.sort(Comparator.comparing(integer -> integer));
//        System.out.println(list);

//        System.out.println(binarySearch(list, 2));

        int[] arr = new int[]{10, 11, 18, 19, 38, 58, 72, 87, 92};
        System.out.println(binarySearch(arr, 63)); // index == 5 -> 63이하의 수들 중 가장 큰 값
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
        }
        else {
            if (searchItem < list.get(mid)) {
                return binarySearch(new ArrayList<>(list.subList(0, mid)), searchItem);
            }
            else {
                return binarySearch(new ArrayList<>(list.subList(mid, list.size())), searchItem);
            }
        }
    }

    private static int binarySearch(int[] list, int target) {
        int l = 0;
        int r = list.length - 1;
        int res = -1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (list[mid] < target) {
                res = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        return res;
    }
}
