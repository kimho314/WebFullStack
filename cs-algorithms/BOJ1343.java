public class BOJ1343 {
    static FastReader SC = new FastReader();

    public static void main(String[] args) {
        String input = SC.nextLine();

        StringBuilder sb = new StringBuilder();
        boolean isAllCovered = true;
        int idx = 0;
        while (idx < input.length()) {
            if (input.charAt(idx) == '.') {
                idx++;
                sb.append('.');
            }
            else {
                int idxOfDot = input.indexOf('.', idx);
                int idx1 = idxOfDot == -1 ? input.length() : idxOfDot;
                String substring = input.substring(idx, idx1);
                if (substring.length() % 2 == 1) {
                    isAllCovered = false;
                    break;
                }
                String str = substring.replaceAll("XXXX", "AAAA").replaceAll("XX", "BB");
                sb.append(str);
                idx = idx1;
            }
        }

        if (!isAllCovered) {
            System.out.println(-1);
        }
        else {
            System.out.println(sb);
        }
    }
}
