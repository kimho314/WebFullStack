import java.util.ArrayList;
import java.util.HashSet;

public class Painting {
    public static void main(String[] args) {
        int n = 8, m = 4;
        int[] section = {2, 3, 6};
        System.out.println(new Painting().solution2(n, m, section));
        n = 5;
        m = 4;
        section = new int[]{1, 3};
        System.out.println(new Painting().solution2(n, m, section));
        n = 4;
        m = 1;
        section = new int[]{1, 2, 3, 4};
        System.out.println(new Painting().solution2(n, m, section));
    }

    public int solution(int n, int m, int[] section) {
        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < section.length; i++) {
            list.add(section[i]);
        }
        for (int i = 1; i <= n; i++) {
            if (i > section[section.length - 1]) {
                break;
            }
            if (!list.contains(i)) {
                continue;
            }

            if (!set.isEmpty() && set.contains(i)) {
                continue;
            }

            int limit = i + (m - 1);
            set.clear();
            for (int j = i; j <= limit; j++) {
                set.add(j);
            }

            answer++;
        }
        return answer;
    }

    public int solution2(int n, int m, int[] section) {
        int answer = 1;
        int roller = section[0];
        for (int i = 1; i < section.length; i++) {
            if (roller + m - 1 < section[i]) {
                answer++;
                roller = section[i];
            }
        }
        return answer;
    }
}
