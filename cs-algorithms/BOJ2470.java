import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {
    static int N;
    static FastReader sc = new FastReader();
    static int[] NUMS;

    public static void main(String[] args) {
        N = sc.nextInt();
        NUMS = new int[N];
        for (int i = 0; i < N; i++) {
            NUMS[i] = sc.nextInt();
        }

        Arrays.sort(NUMS);

        int bestSum = Integer.MAX_VALUE;
        int v1 = 0;
        int v2 = 0;
        for (int i = 0; i < N; i++) {
            // -num 보다 오른쪽에 있는 숫자들 중 가장 왼쪽 숫자의 인덱스 반환
            int res = binarySearch(NUMS, i + 1, N - 1, (-1 * NUMS[i]));

            // 위에서 얻은 인덱스 or 인덱스 -1 중 num과의 합이 가장 0에 가까운 값 선택
            if (i < res - 1 && Math.abs(NUMS[i] + NUMS[res - 1]) < bestSum) {
                bestSum = Math.abs(NUMS[i] + NUMS[res - 1]);
                v1 = NUMS[i];
                v2 = NUMS[res - 1];
            }

            if (res < N && Math.abs(NUMS[i] + NUMS[res]) < bestSum) {
                bestSum = Math.abs(NUMS[i] + NUMS[res]);
                v1 = NUMS[i];
                v2 = NUMS[res];
            }
        }
        System.out.println(v1 + " " + v2);
    }

    static int binarySearch(int[] arr, int left, int right, int key) {
        int res = right + 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < key) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
                res = mid;
            }
        }

        return res;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(s));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
