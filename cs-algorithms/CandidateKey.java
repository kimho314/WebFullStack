import java.util.*;
import java.util.stream.Collectors;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42890">...</a>
 */
public class CandidateKey {
    public static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new CandidateKey().solution(new String[][]{
                        {"100", "ryan", "music", "2"},
                        {"200", "apeach", "math", "2"},
                        {"300", "tube", "computer", "3"},
                        {"400", "con", "computer", "4"},
                        {"500", "muzi", "music", "3"},
                        {"600", "apeach", "music", "2"}
                }
        ));
        System.out.println(new CandidateKey().solution(new String[][]{
                        {"a", "1", "aaa", "c", "ng"},
                        {"a", "1", "bbb", "e", "g"},
                        {"c", "1", "aaa", "d", "ng"},
                        {"d", "2", "bbb", "d", "ng"}
                }
        ));
    }

    public void comb(List<String> list, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (visited[i]) {
                    str.append(list.get(i));
                }
            }
            set.add(str.toString());
        }
        else {
            for (int i = 0; i < n; i++) {
                visited[i] = true;
                comb(list, visited, i + 1, n, r - 1);
                visited[i] = false;
            }
        }
    }

    public int solution(String[][] relation) {
        int answer = 0;

        // get all permutation of column combination
        // column : 1, 2, 3
        // 1, 2, 3, 12, 13, 23, 123
        // column : 1, 2, 3, 4, 5
        // 1, 2, 3, 4, 5, 12, 13, 14, 15, 23, 24, 25, 34, 35, 45, 123, 124, 125, 234, 235, 345, 1234, 1235, 2345, 12345
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < relation[0].length; i++) {
            nums.add(String.valueOf(i));
        }
        boolean[] visited = new boolean[nums.size()];
        for (int r = 1; r < nums.size(); r++) {
            comb(nums, visited, 0, nums.size(), r);
        }
        List<String> collect = set.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        System.out.println(collect);

        // for loop permutation of column combination
        // check if column combination is candidate key
        // condition for candidate key : uniqueness
        Set<String> candidateKeySet = new HashSet<>();
        for (String s : collect) {
            Set<String> keySet = new HashSet<>();
            for (String[] strings : relation) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < strings.length; j++) {
                    if (s.contains(String.valueOf(j))) {
                        key.append(strings[j]);
                    }
                }
                if (!keySet.contains(key.toString())) {
                    keySet.add(key.toString());
                }
                else {
                    break;
                }
            }
            if (keySet.size() == relation.length) {
                if (candidateKeySet.stream().noneMatch(s::contains)) {
                    candidateKeySet.add(s);
                }
            }
        }

        System.out.println(candidateKeySet);
        answer = candidateKeySet.size();
        return answer;
    }
}
