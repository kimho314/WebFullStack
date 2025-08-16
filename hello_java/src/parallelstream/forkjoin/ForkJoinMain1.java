package parallelstream.forkjoin;

import util.MyLogger;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class ForkJoinMain1 {
    public static void main(String[] args) {
        List<Integer> data = IntStream.rangeClosed(1, 8)
                .boxed()
                .toList();
        MyLogger.log("[생성] " + data);

        long startTime = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool(10);
        SumTask task = new SumTask(data);

        Integer result = pool.invoke(task);
        pool.close();

        long endTime = System.currentTimeMillis();

        MyLogger.log("time: " + (endTime - startTime) + "ms, sum: " + result);
        MyLogger.log(pool.toString());
    }
}
