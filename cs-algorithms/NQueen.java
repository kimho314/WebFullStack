import java.util.ArrayList;
import java.util.Objects;

public class NQueen {
    public static void main(String[] args) {
        NQueen nQueen = new NQueen();
        nQueen.dfs(4, 0, new ArrayList<>());
    }


    public boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol) {
        int currentRow = candidate.size();
        for (int idx = 0; idx < currentRow; idx++) {
            if ((Objects.equals(candidate.get(idx), currentCol)) || (Math.abs(candidate.get(idx) - currentCol)) == currentRow - idx) {
                return false;
            }
        }
        return true;
    }

    public void dfs(Integer N, Integer currentRow, ArrayList<Integer> currentCandidate) {
        if (Objects.equals(currentRow, N)) {
            System.out.println(currentCandidate);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isAvailable(currentCandidate, i)) {
                currentCandidate.add(i);
                dfs(N, currentRow + 1, currentCandidate);
                currentCandidate.remove(currentCandidate.size() - 1);
            }
        }
    }

}
