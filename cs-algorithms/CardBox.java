public class CardBox {
    public static void main(String[] args) {
        String[] cards1 = {"i", "drink", "water"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};
        System.out.println(solution(cards1, cards2, goal));

        cards1 = new String[]{"i", "water", "drink"};
        cards2 = new String[]{"want", "to"};
        goal = new String[]{"i", "want", "to", "drink", "water"};
        System.out.println(solution(cards1, cards2, goal));
    }

    public static String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        boolean match = true;
        int idxOfCards1 = 0;
        int idxOfCards2 = 0;
        int idxOfGoal = 0;

        while (idxOfGoal < goal.length) {
            String card1 = idxOfCards1 < cards1.length ? cards1[idxOfCards1] : null;
            String card2 = idxOfCards2 < cards2.length ? cards2[idxOfCards2] : null;
            String str = goal[idxOfGoal];

            boolean match1 = str.equals(card1);
            boolean match2 = str.equals(card2);
            if (match1) {
                idxOfCards1++;
            }
            if (match2) {
                idxOfCards2++;
            }

            idxOfGoal++;

            if (!match1 && !match2) {
                match = false;
                break;
            }
        }

        if (match) {
            answer = "Yes";
        } else {
            answer = "No";
        }
        return answer;
    }
}
