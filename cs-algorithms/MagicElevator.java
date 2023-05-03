import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/148653">...</a>
 * 16 / 10 = 1
 * 16 % 10 = 6
 * 6 / 10 = 0
 * 6 % 10 = 6
 * 2554 / 10 = 255
 * 2554 % 10 = 4
 * 255 / 10 = 25
 * 255 % 10 = 5
 * 25 / 10 = 2
 * 25 % = 5
 * 2 / 10 = 0
 * 2 % 10 = 2
 */
public class MagicElevator {
    public static void main(String[] args) {
        System.out.println(new MagicElevator().solution(2554));
        System.out.println(new MagicElevator().solution(16));
        System.out.println(new MagicElevator().solution(10));
        System.out.println(new MagicElevator().solution(1000));
    }

    public int solution(int storey) {
        int answer = 0;
        List<Integer> splitNum = getSplitNum(storey);
        long zeroCount = splitNum.subList(0, splitNum.size() - 1).stream().filter(it -> it == 0).count();
        if (zeroCount == splitNum.size() - 1) {
            answer = splitNum.get(splitNum.size() - 1);
        }
        else {
            answer = splitNum.stream().mapToInt(Integer::intValue).sum();

            int lastNum = splitNum.get(splitNum.size() - 1);
            int lastNumOfBiggerNum = lastNum + 1;
            int biggerNum = lastNumOfBiggerNum * (int) Math.pow(10, splitNum.size() - 1);
            List<Integer> diffNum = getSplitNum(biggerNum - answer);
            answer = Math.min(answer, (lastNumOfBiggerNum + diffNum.stream().mapToInt(Integer::intValue).sum()));
        }

        return answer;
    }

    public List<Integer> getSplitNum(int num) {
        List<Integer> list = new ArrayList<>();
        do {
            int remainder = num % 10;
            list.add(remainder);
            num /= 10;
        }
        while (num != 0);
        return list;
    }
}
