import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BOJ20291 {
    private static FastReader SC = new FastReader();
    private static int N;
    private static HashMap<String, Integer> MAP = new HashMap<>();

    public static void main(String[] args) {
        input();
        solve();
    }

    private static void solve() {
        ArrayList<String> formats = new ArrayList<>(MAP.keySet());
        formats.sort(Comparator.comparing(it -> it));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < formats.size(); i++) {
            String key = formats.get(i);
            Integer count = MAP.get(key);
            sb.append(key).append(' ').append(count).append('\n');
        }

        System.out.println(sb);
    }

    private static void input() {
        N = SC.nextInt();
        for (int i = 0; i < N; i++) {
            String input = SC.next();
            String format = input.split("\\.")[1];
            MAP.put(format, MAP.getOrDefault(format, 0) + 1);
        }
    }
}
