/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/12952">...</a>
 */
public class NQueen2 {
    public static void main(String[] args) {
        System.out.println(new NQueen2().solution(4));
    }

    static int answer = 0;

    public int solution(int n) {
        int[] col = new int[n + 1];
        recFunc(1, col, n);
        return answer;
    }

    private void recFunc(int k, int[] col, int n) {
        if (k == n + 1) {
            answer++;
        }
        else {
            for (int i = 1; i <= n; i++) {
                boolean isValid = true;
                for (int j = 1; j < k; j++) {
                    if (attackable(k, i, j, col[j])) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    col[k] = i;
                    recFunc(k + 1, col, n);
                    col[k] = 0;
                }
            }
        }
    }

    private boolean attackable(int row1, int col1, int row2, int col2) {
        if (col1 == col2) {
            return true;
        }
        if (row1 + col1 == row2 + col2) {
            return true;
        }
        if (row1 - col1 == row2 - col2) {
            return true;
        }
        return false;
    }
}
