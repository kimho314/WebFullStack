public class BOJ22864 {
    static FastReader SC = new FastReader();
    static int A, B, C, M;

    public static void main(String[] args) {
        A = SC.nextInt();
        B = SC.nextInt();
        C = SC.nextInt();
        M = SC.nextInt();

        int res = 0;
        int stress = 0;
        for (int i = 1; i <= 24; i++) {
            int tmpStress = stress + A;
            if (tmpStress <= M) {
                res += B;
                stress = tmpStress;
            }
            else {
                if (stress - C < 0) {
                    stress = 0;
                }
                else {
                    stress -= C;
                }
            }
        }
        System.out.println(res);
    }
}
