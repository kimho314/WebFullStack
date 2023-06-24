import java.util.Arrays;

public class BOJ2110 {
    static FastReader sc = new FastReader();
    static int N, C;
    static int[] HOMES;

    public static void main(String[] args) {
        N = sc.nextInt();
        C = sc.nextInt();
        HOMES = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            HOMES[i] = sc.nextInt();
        }

        int left = 1;
        int right = 1000000000;
        int res = 0;

        Arrays.sort(HOMES);

        while (left <= right) {
            int mid = (left + right) / 2;

            if (determination(mid)) {
                res = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(res);
    }

    // 공유기 사이 거리 distance 일 때, 설치 가능한 귱유기 갯 수가 C 이상인지 판단
    static boolean determination(int distance) {
        int start = 1;
        int cnt = 1;
        for (int i = 2; i <= N; i++) {
            if (HOMES[i] - HOMES[start] >= distance) {
                start = i;
                cnt++;
            }
        }
        return cnt >= C;
    }
}
