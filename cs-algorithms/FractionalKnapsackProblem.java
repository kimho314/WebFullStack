import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsackProblem {
    public static void main(String[] args) {
        // {weight,value}
        Integer[][] items = {{10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5}};
        var result = knapsackFunc(items, 30.0);
        System.out.println("result : " + result);
    }

    public static double knapsackFunc(Integer[][] list, double capacity) {
        double totalValue = 0.0;
        double fraction;

        // compare value / weight
        Arrays.sort(list, Comparator.comparingDouble(o -> ((double) o[1] / (double) o[0])));

        for (int i = list.length - 1; i >= 0; i--) {
            if (capacity - (double) list[i][0] > 0.0) { // no need to split object
                capacity -= (double) list[i][0];
                totalValue += (double) list[i][1];
                System.out.println("w : " + list[i][0] + " v : " + list[i][1]);
            } else { // need to split object
                fraction = capacity / (double) list[i][0];
                totalValue += (double) list[i][1] * fraction;
                System.out.println("w : " + list[i][0] + " v : " + list[i][1]);
                break;
            }
        }

        return totalValue;
    }
}
