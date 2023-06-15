import java.util.Scanner;

public class BOJ9663 {

    static Integer N, ANS;

    static int[] SELECTED;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        N = sc.nextInt();
        ANS = 0;
        SELECTED = new int[N + 1];
        rec_func(1);
        System.out.println(ANS);
    }

    static boolean isAvailable(int r1, int c1, int r2, int c2) {
        if (c1 == c2) {
            return false;
        }
        if (r1 - c1 == r2 - c2) {
            return false;
        }
        if (r1 + c1 == r2 + c2) {
            return false;
        }
        return true;
    }

    static void rec_func(int row) {
        if (row == N + 1) { // 다 골랐다!
            ANS++;
        }
        else {
            for (int cand = 1; cand <= N; cand++) {
                // row 번째에 cand 가 올 수 있으면

                boolean available = true;
                for (int i = 1; i < row; i++) {
                    if (!isAvailable(row, cand, i, SELECTED[i])) {
                        available = false;
                        break;
                    }
                }

                if (available) {
                    SELECTED[row] = cand;
                    // row+1 번부터 M 번까지 잘 채워주는 함수를 호출해준다.
                    rec_func(row + 1);
                    SELECTED[row] = 0;
                }
            }
        }
    }
}
