import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/49993">...</a>
 */
public class SkillTree {
    public static void main(String[] args) {
        System.out.println(new SkillTree().solution("CBD", new String[]{"BACDE", "CBADF", "AECB", "BDA"}));
    }

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (String s : skill_trees) {
            List<String> collect = Arrays.stream(s.split(""))
                    .filter(skill::contains)
                    .collect(Collectors.toList());
            boolean flag = true;
            for (int i = 0; i < collect.size(); i++) {
                if (!collect.get(i).equals(String.valueOf(skill.charAt(i)))) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer++;
            }
        }

        return answer;
    }

    public int solution2(String skill, String[] skill_trees) {
        int answer;
        ArrayList<String> skillTrees = new ArrayList<>(Arrays.asList(skill_trees));

        skillTrees.removeIf(s -> skill.indexOf(s.replaceAll("[^" + skill + "]", "")) != 0);
        answer = skillTrees.size();
        return answer;
    }
}
