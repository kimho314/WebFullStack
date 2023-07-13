import java.util.Arrays;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/172928">...</a>
 */
public class WalkingPark {
    public static class Point {
        public int x;
        public int y;

        Point() {
            this.x = 0;
            this.y = 0;
        }

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point move(String op, int n) {
            switch (op) {
                case "N":
                    return new Point(this.x - n, this.y);
                case "S":
                    return new Point(this.x + n, this.y);
                case "W":
                    return new Point(this.x, this.y - n);
                case "E":
                    return new Point(this.x, this.y + n);
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new WalkingPark().solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 2", "S 2", "W 1"})));
        System.out.println(Arrays.toString(new WalkingPark().solution(new String[]{"SOO", "OXX", "OOO"}, new String[]{"E 2", "S 2", "W 1"})));
        System.out.println(Arrays.toString(new WalkingPark().solution(new String[]{"OSO", "OOO", "OXO", "OOO"}, new String[]{"E 2", "S 3", "W 1"})));
        System.out.println(Arrays.toString(new WalkingPark().solution(new String[]{"OXXO", "XSXO", "XXXX"}, new String[]{"E 1", "S 1"})));
    }

    public int[] solution(String[] park, String[] routes) {
        int[] answer;

        Point start = new Point();
        // find start point
        for (int x = 0; x < park.length; x++) {
            if (park[x].contains("S")) {
                int y = park[x].indexOf('S');
                start.x = x;
                start.y = y;
            }
        }

        Point point = new Point(start.x, start.y);
        for (String route : routes) {
            String op = route.split(" ")[0];
            int n = Integer.parseInt(route.split(" ")[1]);
            Point to = point.move(op, n);
            if (!isAvailable(to, point, park, op)) {
                continue;
            }
            point = to;
        }
        answer = new int[]{point.x, point.y};

        return answer;
    }

    public boolean isAvailable(Point to, Point from, String[] park, String op) {
        if (to.x < 0 || to.x >= park.length || to.y < 0 || to.y >= park[0].length()) {
            return false;
        }

        boolean res = true;
        if (op.equals("E") || op.equals("W")) {
            for (int y = Math.min(from.y, to.y); y <= Math.max(from.y, to.y); y++) {
                if (park[from.x].charAt(y) == 'X') {
                    res = false;
                    break;
                }
            }
        }
        else {
            for (int x = Math.min(from.x, to.x); x <= Math.max(from.x, to.x); x++) {
                if (park[x].charAt(from.y) == 'X') {
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
}
