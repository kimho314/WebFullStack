import java.util.ArrayList;
import java.util.Objects;

public class SequentialSearch {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add((int) (Math.random() * 100));
        }
        System.out.println(list);
        System.out.println(searchFunc(list, 30));
        System.out.println(searchFunc(list, 66));
    }

    public static Integer searchFunc(ArrayList<Integer> dataList, Integer searchItem) {
        for (int i = 0; i < dataList.size(); i++) {
            if (Objects.equals(dataList.get(i), searchItem)) {
                return i;
            }
        }
        return -1;
    }
}
