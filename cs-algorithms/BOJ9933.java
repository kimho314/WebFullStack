import java.util.Arrays;

public class BOJ9933 {
    private static FastReader SC = new FastReader();
    private static String[] STR;
    private static int N;

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        String res = "";
        for (int i = 0; i < N; i++) {
            boolean isPalindrome = isPalindrome(STR[i]);
            if (isPalindrome) {
                res = STR[i];
                break;
            }

            char[] ch1 = STR[i].toCharArray();
            Arrays.sort(ch1);
            for (int j = i + 1; j < N; j++) {
                char[] ch2 = STR[j].toCharArray();
                Arrays.sort(ch2);
                boolean flag = true;
                for (int k = 0; k < ch1.length; k++) {
                    if (ch1[k] != ch2[k]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    res = STR[i];
                    break;
                }
            }
        }

        System.out.println(res.length() + " " + res.charAt(res.length() / 2));
    }

    private static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static void input() {
        N = SC.nextInt();
        STR = new String[N];
        for (int i = 0; i < N; i++) {
            STR[i] = SC.next();
        }
    }
}
