import java.util.Stack;

public class BOJ17413 {
    static FastReader SC = new FastReader();
    static String INPUT;

    public static void main(String[] args) {
        init();
        solve();
    }

    private static void solve() {
        boolean isTag = false;
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < INPUT.length(); i++) {
            if (INPUT.charAt(i) == '<') {
                isTag = true;
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(INPUT.charAt(i));
            }
            else if (INPUT.charAt(i) == '>') {
                isTag = false;
                sb.append(INPUT.charAt(i));
            }
            else {
                if (isTag) {
                    sb.append(INPUT.charAt(i));
                }
                if (!isTag) {
                    if (INPUT.charAt(i) == ' ') {
                        while (!stack.isEmpty()) {
                            sb.append(stack.pop());
                        }
                        sb.append(INPUT.charAt(i));
                    }
                    else {
                        stack.push(INPUT.charAt(i));
                    }
                }
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }

    private static void init() {
        INPUT = SC.nextLine();
    }
}
