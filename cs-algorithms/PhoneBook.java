import java.util.Arrays;
import java.util.HashSet;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42577">...</a>
 */
public class PhoneBook {
    public static void main(String[] args) {
        System.out.println(new PhoneBook().solution(new String[]{"119", "97674223", "1195524421"}));
        System.out.println(new PhoneBook().solution(new String[]{"123", "456", "789"}));
        System.out.println(new PhoneBook().solution(new String[]{"12", "123", "1235", "567", "88"}));
        System.out.println(new PhoneBook().solution(new String[]{"123", "1005", "1006", "1007"}));
    }

    public boolean solution(String[] phone_book) {
        boolean answer = true;
        HashSet<String> set = new HashSet<>(Arrays.asList(phone_book));
        for (String s : phone_book) {
            for (int j = 1; j < s.length(); j++) {
                if (set.contains(s.substring(0, j))) {
                    return false;
                }
            }
        }

        return answer;
    }

    public boolean solution2(String[] phoneBook) {
        for (int i = 0; i < phoneBook.length - 1; i++) {
            for (int j = i + 1; j < phoneBook.length; j++) {
                if (phoneBook[i].startsWith(phoneBook[j])) {
                    return false;
                }
                if (phoneBook[j].startsWith(phoneBook[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
