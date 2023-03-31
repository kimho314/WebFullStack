import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/155651
 */
public class BookingHotel {

    public static void main(String[] args) {
        String[][] input1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"},
            {"18:20", "21:20"}};
        int solution = solution(input1);
    }

    public static int solution(String[][] book_time) {
        int answer = 0;
        final String breakTime = "00:10";
        List<String[]> sortedBookTime = getSortedList(book_time);
        for (int i = 0; i < sortedBookTime.size() - 1; i++) {
            String time2 = sortedBookTime.get(i)[1];
            String[] split = time2.split(":");
            int addedMin = Integer.parseInt(split[1]) + Integer.parseInt(breakTime.split(":")[1]);
            String joinTime = String.join(":", split[0], String.valueOf(addedMin));
            System.out.println("joinTime = " + joinTime);

            for (int j = i + 1; j < sortedBookTime.size(); j++) {
                
            }
        }
        return answer;
    }

    private static List<String[]> getSortedList(String[][] book_time) {
        List<String[]> strings = Arrays.stream(book_time)
            .peek(s -> System.out.println(s[0]))
            .sorted(Comparator.comparing(s -> s[0]))
            .toList();
        System.out.println("==========");
        strings.forEach(s -> System.out.println(s[0]));
        return strings;
    }
}
