public class BOJ1969 {
    static FastReader SC = new FastReader();
    static int N, M;
    static String[] DNA;

    public static void main(String[] args) {
        N = SC.nextInt();
        M = SC.nextInt();
        DNA = new String[N];
        for (int i = 0; i < N; i++) {
            DNA[i] = SC.nextLine();
        }

        int max = 0;
        int hd = 0;
        for (int i = 0; i < M; i++) {
            int a = 0, t = 0, g = 0, c = 0;
            for (int j = 0; j < N; j++) {
                char ch = DNA[j].charAt(i);
                if (ch == 'A') {
                    a++;
                }
                if (ch == 'T') {
                    t++;
                }
                if (ch == 'G') {
                    g++;
                }
                if (ch == 'C') {
                    c++;
                }
            }

            max = Math.max(Math.max(a, c), Math.max(g, t));
            hd += (N - max);
            System.out.print(toChar(a, t, g, c, max));
        }
        System.out.println("\n" + hd);
    }

    private static char toChar(int a, int t, int g, int c, int max) {
        if (max == a) {
            return 'A';
        }
        else if (max == c) {
            return 'C';
        }
        else if (max == g) {
            return 'G';
        }
        else {
            return 'T';
        }
    }

}
