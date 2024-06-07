public class Price_20240607 {
    public static void main(String[] args) {
        System.out.println(getPrice("1234"));
        System.out.println(getPrice("123"));
        System.out.println(getPrice("123456"));
        System.out.println(getPrice("1234567"));
    }

    private static String getPrice(String input) {
        String output = "";
        int cnt = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            cnt++;
            output = input.charAt(i) + output;
            if (i > 0 && cnt % 3 == 0) {
                output = "," + output;
            }
        }
        return output;
    }
}
