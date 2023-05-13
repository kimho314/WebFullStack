import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }
        permutation(list, 0, arr.length, 3);

    }

    // 순열 메서드
    static void permutation(List<Integer> arr, int depth, int n, int r) {
        if (depth == r) {
            System.out.println(arr.subList(0, depth));
            return;
        }

        for (int i = depth; i < n; i++) {
            Collections.swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            Collections.swap(arr, depth, i);
        }
    }

}
