public class Painting {
    public static void main(String[] args) {
        int n = 8, m = 4;
        int[] section = {2, 3, 6};
        System.out.println(new Painting().solution(n, m, section));
        n = 5;
        m = 4;
        section = new int[]{2, 3, 6};
        System.out.println(new Painting().solution(n, m, section));
        n = 4;
        m = 1;
        section = new int[]{1, 2, 3, 4};
        System.out.println(new Painting().solution(n, m, section));
    }

    public int solution(int n, int m, int[] section) {
        int answer = 0;
        for (int i = 0; i < section.length; i++) {
            answer++;
            int nextNum = section[i] + (m - 1);
            if (nextNum >= n || nextNum >= section[section.length - 1]) {
                break;
            }
        }
        return answer;
    }
}
