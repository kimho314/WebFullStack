public class BOJ1476 {
    static int E, S, M;
    static FastReader SC = new FastReader();

    public static void main(String[] args) {
        E = SC.nextInt();
        S = SC.nextInt();
        M = SC.nextInt();

        int year = 1;
        while (true) {
            if ((year - E) % 15 == 0 && (year - S) % 28 == 0 && (year - M) % 19 == 0) {
                break;
            }
            year++;
        }
        System.out.println(year);
    }
}
