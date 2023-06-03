import java.util.Arrays;

public class ClosestSameCharactor {
    public static void main(String[] args) {
        String s = "banana";
        System.out.println(Arrays.toString(new ClosestSameCharactor().solution(s)));
        s = "foobar";
        System.out.println(Arrays.toString(new ClosestSameCharactor().solution(s)));
    }

    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            String substring = s.substring(0, i);
            if (substring.length() == 0) {
                answer[i] = -1;
            }
            else {
                int lastIndexOf = substring.lastIndexOf(s.charAt(i));
                answer[i] = lastIndexOf == -1 ? -1 : i - lastIndexOf;
            }
        }
        return answer;
    }
}
