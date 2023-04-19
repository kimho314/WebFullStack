import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/147354
 */
public class TableHashFunction {
    public static void main(String[] args) {
        System.out.println(new TableHashFunction().solution(
                new int[][]{{2, 2, 6}, {1, 5, 10}, {4, 2, 9}, {3, 8, 3}},
                2, 2, 3
        ));
    }

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) {
                return o2[0] - o1[0];
            }
            return o1[col - 1] - o2[col - 1];
        });
        System.out.println(Arrays.deepToString(data));
        answer = sumOfI(data, row_begin);
        for (int i = row_begin + 1; i <= row_end; i++) {
            answer = answer ^ sumOfI(data, i);
        }
        return answer;
    }

    public int sumOfI(int[][] data, int i) {
        int[] datum = data[i - 1];
        int sum = 0;
        for (int d : datum) {
            sum += d % i;
        }
        return sum;
    }
}
