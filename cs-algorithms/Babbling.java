public class Babbling {
    public static void main(String[] args) {
        System.out.println(new Babbling().solution(new String[]{"aya", "yee", "u", "maa"}));
        System.out.println(new Babbling().solution(new String[]{"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"}));
    }

    public int solution(String[] babbling) {
        int answer = 0;
        for (String string : babbling) {
            string = string.replaceAll("ayaaya|yeye|woowoo|mama", " ");
            string = string.replaceAll("aya|ye|woo|ma", "");
            if (string.isEmpty())
                answer++;
        }
        return answer;
    }
}
