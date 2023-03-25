import java.util.ArrayList;

public class QuickSortExample {
    public static void main(String[] args) {
        ArrayList<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add((int) (Math.random() * 100));
        }
        System.out.println(dataList);
        ArrayList<Integer> sortedList = sort(dataList);
        System.out.println(sortedList);
    }

    public static ArrayList<Integer> sort(ArrayList<Integer> dataList) {
        if (dataList.size() <= 1) {
            return dataList;
        }

        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();

        int pivot = dataList.get(0);
        for (int i = 1; i < dataList.size(); i++) {
            if (dataList.get(i) > pivot) {
                rightList.add(dataList.get(i));
            } else {
                leftList.add(dataList.get(i));
            }
        }

        ArrayList<Integer> mergedList = new ArrayList<>();
        mergedList.addAll(sort(leftList));
        mergedList.add(pivot);
        mergedList.addAll(sort(rightList));

        return mergedList;
    }
}
