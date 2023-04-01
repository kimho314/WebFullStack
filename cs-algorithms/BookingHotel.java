import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/155651
 */
public class BookingHotel {
    private static final String BREAK_TIME = "10";

    public static void main(String[] args) {
        String[][] input1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"},
                {"18:20", "21:20"}};
        int solution = solution(input1);
        System.out.println(solution);
        System.out.println("========");
        String[][] input2 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
        int solution2 = solution(input2);
        System.out.println(solution2);
        System.out.println("========");
        String[][] input3 = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        int solution3 = solution(input3);
        System.out.println(solution3);
        System.out.println("========");
        String[][] input4 = {{"00:00", "12:30"}};
        int solution4 = solution(input4);
        System.out.println(solution4);
    }

    public static int solution(String[][] book_time) {
        // [입실시간, 정리완료시간] 을 넣을 int 2차배열 생성 및 값 주입
        int[][] bookTime = new int[book_time.length][];
        for (int i = 0; i < book_time.length; i++) {
            bookTime[i] = new int[]{parseTime(book_time[i][0]), parseTime(book_time[i][1]) + 10};
        }

        // 입실시간 오름차순 정렬
        Arrays.sort(bookTime, (a, b) -> a[0] - b[0]);

        // 현재 이용중인 방의 퇴실시간정보 사용을 위해 퇴실시간 min-heap 선언
        PriorityQueue<int[]> inUse = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int ans = 0;

        for (int[] ints : bookTime) {

            // 다음입실시간 까지 정리가 완료되는방 poll처리
            while (!inUse.isEmpty() && inUse.peek()[1] <= ints[0]) {
                inUse.poll();
            }
            inUse.add(ints);
            ans = Math.max(ans, inUse.size());
        }
        return ans;
    }

    // time String을 int값으로 변환
    public static int parseTime(String time) {
        String[] hhmm = time.split(":");
        int hour = Integer.parseInt(hhmm[0]), min = Integer.parseInt(hhmm[1]);
        return hour * 60 + min;
    }
}
