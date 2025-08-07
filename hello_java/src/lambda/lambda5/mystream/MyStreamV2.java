package lambda.lambda5.mystream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStreamV2 {
    private List<Integer> internalList;

    public MyStreamV2(List<Integer> internalList) {
        this.internalList = internalList;
    }

    public static MyStreamV2 of(List<Integer> internalList) {
        return new MyStreamV2(internalList);
    }

    public MyStreamV2 filter(Predicate<Integer> predicate) {
        List<Integer> filtered = new ArrayList<>();
        for (Integer elem : internalList) {
            if (predicate.test(elem)) {
                filtered.add(elem);
            }
        }

        return MyStreamV2.of(filtered);
    }

    public MyStreamV2 map(Function<Integer, Integer> mapper) {
        List<Integer> mapped = new ArrayList<>();
        for (Integer elem : internalList) {
            mapped.add(mapper.apply(elem));
        }

        return MyStreamV2.of(mapped);
    }

    public List<Integer> toList() {
        return internalList;
    }
}
