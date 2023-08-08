import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WeaponOfKnight {
    public static void main(String[] args) {
        System.out.println(new WeaponOfKnight().solution(5, 3, 2));
        System.out.println(new WeaponOfKnight().solution(10, 3, 2));
    }

    public int solution(int number, int limit, int power) {
        int answer;
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            Integer force = getForce(i);
            if (force > limit) {
                force = power;
            }
            list.add(force);
        }
        answer = list.stream().reduce(0, Integer::sum);
        return answer;
    }

    private Integer getForce(int num) {
        int cnt;
        if (num == 1) {
            cnt = 1;
        }
        else {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    set.add(i);
                    set.add(num / i);
                }
            }
            cnt = set.size();
        }
        return cnt;
    }
}
