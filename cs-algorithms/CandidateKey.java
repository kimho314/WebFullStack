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
        System.out.println("=============");
        System.out.println(new CandidateKey().solution(new String[][]{
                        {"a", "1", "aaa", "c", "ng"},
                        {"a", "1", "bbb", "e", "g"},
                        {"c", "1", "aaa", "d", "ng"},
                        {"d", "2", "bbb", "d", "ng"}
                }
        ));
        System.out.println("=============");
        System.out.println(new CandidateKey().solution(new String[][]{
                        {"a", "aa"},
                        {"aa", "a"},
                        {"a,a"},
                }
        ));
    }


    public void combination(List<String> list, boolean[] visited, int start, int depth, int r) {
        if (depth == r) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (visited[i]) {
                    sb.append(list.get(i));
                }
            }
            set.add(sb.toString());
        }
        for (int i = start; i < list.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(list, visited, i + 1, depth + 1, r);
                visited[i] = false;
            }
        }
    }

    public int solution(String[][] relation) {
        int answer;

        // get all permutation of column combination
        // column : 1, 2, 3
        // 1, 2, 3, 12, 13, 23, 123
        // column : 1, 2, 3, 4, 5
        // 1, 2, 3, 4, 5, 12, 13, 14, 15, 23, 24, 25, 34, 35, 45, 123, 124, 125, 234, 235, 345, 1234, 1235, 2345, 12345
        set.clear();
        List<String> nums = new ArrayList<>();
        for (int i = 0; i < relation[0].length; i++) {
            nums.add(String.valueOf(i));
        }
        boolean[] visited = new boolean[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            combination(nums, visited, 0, 0, i + 1);
        }
        List<String> sortedKeys = set.stream()
                .sorted()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println(sortedKeys);

        // for loop permutation of column combination
        // check if column combination is candidate key
        // condition for candidate key : uniqueness
        Set<String> candidateKeySet = new HashSet<>();
        for (String s : sortedKeys) {
            Set<String> keySet = new HashSet<>();
            for (String[] row : relation) {
                StringBuilder key = new StringBuilder();
                for (int j = 0; j < row.length; j++) {
                    if (s.contains(String.valueOf(j))) {
                        key.append(row[j]);
                    }
                }
                if (!keySet.contains(key.toString())) {
                    keySet.add(key.toString());
                }
                else {
                    break;
                }
            }
            boolean isUnique = keySet.size() == relation.length;
            boolean isMinimality = true;
            if (!candidateKeySet.isEmpty()) {
                // s is key meets uniqueness
                // if s doesn't hava all elements of other candidate key then s meets minimality
                List<List<String>> list = candidateKeySet.stream()
                        .map(str -> List.of(str.split("")))
                        .collect(Collectors.toList());
                isMinimality = list.stream().noneMatch(it -> new HashSet<>(Arrays.asList(s.split(""))).containsAll(it));
            }

            if (isUnique && isMinimality) {
                candidateKeySet.add(s);
            }
        }

        System.out.println(candidateKeySet);
        answer = candidateKeySet.size();
        return answer;
    }
}
