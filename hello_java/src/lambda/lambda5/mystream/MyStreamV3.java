package lambda.lambda5.mystream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStreamV3<T> {
    private List<T> internalList;

    public MyStreamV3(List<T> internalList) {
        this.internalList = internalList;
    }

    public static <T> MyStreamV3<T> of(List<T> internalList) {
        return new MyStreamV3<>(internalList);
    }

    public MyStreamV3<T> filter(Predicate<T> predicate) {
        List<T> filtered = new ArrayList<>();
        for (T elem : internalList) {
            if (predicate.test(elem)) {
                filtered.add(elem);
            }
        }

        return MyStreamV3.of(filtered);
    }

    public <R> MyStreamV3<R> map(Function<T, R> mapper) {
        List<R> mapped = new ArrayList<>();
        for (T elem : internalList) {
            mapped.add(mapper.apply(elem));
        }

        return MyStreamV3.of(mapped);
    }

    public List<T> toList() {
        return internalList;
    }

    public void forEach(Consumer<T> consumer) {
        for (T elem : internalList) {
            consumer.accept(elem);
        }
    }

    public T getFirst() {
        return internalList.getFirst();
    }
}
