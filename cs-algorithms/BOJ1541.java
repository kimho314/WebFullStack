public class BOJ1541 {
    static FastReader SC = new FastReader();

    public static void main(String[] args) {
        int sum = Integer.MAX_VALUE;
        String[] subtraction = SC.nextLine().split("-");

        for (int i = 0; i < subtraction.length; i++) {
            int tmp = 0;

            String[] addition = subtraction[i].split("\\+");

            for (int j = 0; j < addition.length; j++) {
                tmp += Integer.parseInt(addition[j]);
            }

            if (sum == Integer.MAX_VALUE) {
                sum = tmp;
            }
            else {
                sum -= tmp;
            }
        }

        System.out.println(sum);
    }
}
