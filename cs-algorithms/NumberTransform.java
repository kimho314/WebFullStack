import java.util.HashSet;

public class NumberTransform {
    public static void main(String[] args) {
        int x = 10;
        int y = 40;
        int n = 5;
        int result = answer(x, y, n);
        System.out.println(result);

        x = 10;
        y = 40;
        n = 30;
        result = answer(x, y, n);
        System.out.println(result);

        x = 2;
        y = 5;
        n = 4;
        result = answer(x, y, n);
        System.out.println(result);

        x = 2;
        y = 2;
        n = 4;
        result = answer(x, y, n);
        System.out.println(result);
    }

    public static int answer(int x, int y, int n) {
        int cnt = 0;
        HashSet<Integer> curr = new HashSet<>(), next;
        curr.add(x);

        while (!curr.isEmpty()) {
            if (curr.contains(y)) {
                return cnt;
            }
            next = new HashSet<>();
            for (int val : curr) {
                int pVal = val + n;
                int dVal = val * 2;
                int tVal = val * 3;
                if (pVal <= y) {
                    next.add(pVal);
                }
                if (dVal <= y) {
                    next.add(dVal);
                }
                if (tVal <= y) {
                    next.add(tVal);
                }
            }
            curr = next;
            cnt++;
        }
        return -1;
    }
}
