import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/155652
 */
public class SecretCode {
    public static void main(String[] args) {
        int index = 5;
        String s = "aukks";
        String skip = "wbqd";
        String[] sSplit = s.split("");
        Arrays.stream(sSplit)
                .forEach(it -> System.out.println(
                        it + "," + (int) it.charAt(0) + "," + (char) ((int) it.charAt(0) + index) + "," +
                                ((int) it.charAt(0) + index))
                );
        List<String> collect = Arrays.stream(skip.split("")).sorted().collect(Collectors.toList());
        System.out.println(collect);
    }
}
