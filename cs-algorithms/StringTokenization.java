public class StringTokenization {
    public static void main(String[] args) {
        System.out.println(new StringTokenization().solution("banana"));
        System.out.println(new StringTokenization().solution("abracadabra"));
        System.out.println(new StringTokenization().solution("aaabbaccccabba"));
    }

    public int solution(String s) {
        int answer = 0;
        int cur = 0;
        while (cur < s.length()) {
            char ch = s.charAt(cur);
            int cntX = 1;
            int cntNotX = 0;
            for (int i = cur + 1; i < s.length(); i++) {
                if (cntX == cntNotX) {
                    break;
                }

                if (s.charAt(i) == ch) {
                    cntX++;
                }
                else {
                    cntNotX++;
                }
            }
            answer++;
            cur += (cntX + cntNotX);
        }
        return answer;
    }
}
