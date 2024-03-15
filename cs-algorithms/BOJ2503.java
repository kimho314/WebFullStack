public class BOJ2503 {
    static FastReader SC = new FastReader();
    static int N;

    static class Data {
        int num;
        int strikes;
        int balls;

        public Data(int _num, int _strikes, int _balls) {
            this.num = _num;
            this.strikes = _strikes;
            this.balls = _balls;
        }
    }

    public static void main(String[] args) {
        N = SC.nextInt();
        Data[] inputs = new Data[N];
        for (int i = 0; i < N; i++) {
            int num = SC.nextInt();
            int strike = SC.nextInt();
            int ball = SC.nextInt();

            Data data = new Data(num, strike, ball);
            inputs[i] = data;
        }

        int cnt = 0;
        for (int i = 100; i <= 999; i++) {
            if (!isValidNumber(i)) {
                continue;
            }

            boolean matched = true;
            for (int j = 0; j < N; j++) {
                Data data = inputs[j];
                matched = isMatched(i, data);
//                System.out.println("i = " + i + " data = " + data.num + " matched = " + matched);
                if (!matched) {
                    break;
                }
            }
            if (matched) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    private static boolean isValidNumber(int num) {
        int figure1 = num / 100;
        int figure2 = num % 100 / 10;
        int figure3 = num % 100 % 10;

        return ((figure1 != figure2) && (figure2 != figure3) && (figure1 != figure3)) && (figure1 > 0 && figure2 > 0 && figure3 > 0);
    }

    private static boolean isMatched(int num, Data test) {
        char[] tmpNum = String.valueOf(num).toCharArray();

        int strike = 0;
        int ball = 0;
        char[] testNum = String.valueOf(test.num).toCharArray();
        for (int i = 0; i < tmpNum.length; i++) {
            int index = String.valueOf(num).indexOf(testNum[i]);
            if (index == -1) {
                continue;
            }

            if (index == i) {
                strike++;
            }
            if (index != i) {
                ball++;
            }
        }

        return (strike == test.strikes) && (ball == test.balls);
    }
}
