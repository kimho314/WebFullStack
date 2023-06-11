import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Trainers {
    public static void main(String[] args) {
        System.out.println(new Trainers().solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
        System.out.println(new Trainers().solution(5, new int[]{2, 4}, new int[]{3}));
        System.out.println(new Trainers().solution(3, new int[]{3}, new int[]{1}));
        System.out.println(new Trainers().solution(3, new int[]{1, 3}, new int[]{3}));
    }

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n + 1];
        Arrays.fill(students, 1);
        for (int idx : lost) {
            students[idx] = 0;
        }
        for (int idx : reserve) {
            students[idx]++;
        }

        List<Integer> newReserve = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (students[i] >= 2) {
                newReserve.add(i);
            }
        }

        for (int i = 1; i <= n; i++) {
            if (students[i] == 0) {
                int before = i - 1;
                int after = i + 1;
                if (newReserve.contains(before) && !newReserve.contains(after)) {
                    students[i]++;
                    students[before]--;
                    newReserve.remove((Integer) before);
                }
                if (!newReserve.contains(before) && newReserve.contains(after)) {
                    students[i]++;
                    students[after]--;
                    newReserve.remove((Integer) after);
                }
                if (newReserve.contains(before) && newReserve.contains(after)) {
                    students[i]++;
                    students[before]--;
                    newReserve.remove((Integer) before);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (students[i] >= 1) {
                answer++;
            }
        }
        return answer;
    }

    public int solution2(int n, int[] lost, int[] reserve) {
        int[] people = new int[n];
        int answer = n;

        for (int l : lost)
            people[l - 1]--;
        for (int r : reserve)
            people[r - 1]++;

        for (int i = 0; i < people.length; i++) {
            if (people[i] == -1) {
                if (i - 1 >= 0 && people[i - 1] == 1) {
                    people[i]++;
                    people[i - 1]--;
                }
                else if (i + 1 < people.length && people[i + 1] == 1) {
                    people[i]++;
                    people[i + 1]--;
                }
                else
                    answer--;
            }
        }
        return answer;
    }
}
