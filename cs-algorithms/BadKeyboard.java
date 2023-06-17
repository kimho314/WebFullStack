import java.util.Arrays;

public class BadKeyboard {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new BadKeyboard().solution(new String[]{"ABACD", "BCEFD"}, new String[]{"ABCD", "AABB"})));
        System.out.println(Arrays.toString(new BadKeyboard().solution(new String[]{"AA"}, new String[]{"B"})));
        System.out.println(Arrays.toString(new BadKeyboard().solution(new String[]{"AGZ", "BSSS"}, new String[]{"ASA", "BGZ"})));
    }

    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int cnt = 0;
            for (int j = 0; j < target.length(); j++) {
                String str = String.valueOf(target.charAt(j));
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < keymap.length; k++) {
                    int index = keymap[k].indexOf(str);
                    if (index != -1) {
                        min = Math.min(index, min);
                    }
                }

                if (min == Integer.MAX_VALUE) {
                    cnt = -1;
                    break;
                }
                else {
                    cnt += (min + 1);
                }
            }

            answer[i] = cnt;
        }
        return answer;
    }
}
