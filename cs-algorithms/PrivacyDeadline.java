import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/150370#
 */
public class PrivacyDeadline {
    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

        int[] solution = solution(today, terms, privacies);
        System.out.println("solution");
        Arrays.stream(solution).forEach(System.out::println);
        System.out.println("================================");
        today = "2020.01.01";
        terms = new String[]{"Z 3", "D 5"};
        privacies = new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};
        solution = solution(today, terms, privacies);
        System.out.println("solution");
        Arrays.stream(solution).forEach(System.out::println);
    }

    public static int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        LocalDate parsedToday = LocalDate.parse(today, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        for (int i = 0; i < privacies.length; i++) {
            String[] split = privacies[i].split(" ");
            LocalDate createdDate = LocalDate.parse(split[0], DateTimeFormatter.ofPattern("yyyy.MM.dd"));
            String policy = split[1];

            long limit = 0L;
            for (String term : terms) {
                if (term.contains(policy)) {
                    limit = Long.parseLong(term.split(" ")[1]);
                }
            }

            LocalDate termExpirationDate = createdDate.plusMonths(limit);
            if (termExpirationDate.getDayOfMonth() == 1) {
                termExpirationDate = LocalDate.of(termExpirationDate.getYear(), termExpirationDate.getMonth().minus(1),
                        28);
            } else {
                termExpirationDate = termExpirationDate.minusDays(1);
            }
            System.out.println(
                    "i : " + i + " createdDate : " + createdDate + " policy : " + policy + " limit : " + limit
                            + " today : " + parsedToday + " termExpirationDate : " + termExpirationDate);
            if (parsedToday.isAfter(termExpirationDate)) {
                list.add(i + 1);
            }
        }

        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
