import java.util.ArrayList;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/148652
 * https://school.programmers.co.kr/questions/44982 -> hint
 * 1 1 0 1 1 -> 4 (4^1)
 * 11011 11011 00000 11011 11011 -> 4 * 4 = 16 (4^2)
 * 11011 11011 00000 11011 11011  11011 11011 00000 11011 11011  000000 00000 00000 00000 00000  11011 11011 00000 11011 11011  11011 11011 00000 11011 11011 -> 16 * 4 = 64 (4^3)
 */
public class CantorBitArray {
    private static final List<String> list = new ArrayList<>(List.of("00000", "11011"));

    public static void main(String[] args) {
        System.out.println(new CantorBitArray().getCantor(1));
        System.out.println(new CantorBitArray().getCantor(2));
        System.out.println(new CantorBitArray().getCantor(3));
        System.out.println(new CantorBitArray().solution(2, 4, 17));
        System.out.println(new CantorBitArray().solution(1, 1, 5));
    }

    public int solution(int n, long l, long r) {
        int answer = 0;
        String cantor = getCantor(n);
        long cnt = 1;
        for (Character c : cantor.toCharArray()) {
            if (cnt >= l && cnt <= r) {
                if (c.toString().equals("1")) {
                    answer++;
                }
            }
            cnt++;
        }
        return answer;
    }


    public String getCantor(int n) {
        if (n == 1) {
            return "11011";
        }
        else {
            return convert(getCantor(n - 1));
        }
    }

    public String convert(String input) {
        StringBuilder sb = new StringBuilder();
        String[] split = input.split("");
        for (String s : split) {
            sb.append(list.get(Integer.parseInt(s)));
        }
        return sb.toString();
    }
}
