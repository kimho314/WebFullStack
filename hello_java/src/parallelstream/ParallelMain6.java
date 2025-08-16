package parallelstream;

import util.MyLogger;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class ParallelMain6 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService requestPool = Executors.newFixedThreadPool(100);

        ExecutorService logicPool = Executors.newFixedThreadPool(400);

        int nThreads = 3;
        for (int i = 1; i <= nThreads; i++) {
            String requestName = "request" + i;
            requestPool.submit(() -> logic(requestName, logicPool));
            Thread.sleep(100);
        }
        requestPool.close();
        logicPool.close();
    }

    private static void logic(String requestName, ExecutorService es) {
        MyLogger.log("[" + requestName + " ] START");
        long startTime = System.currentTimeMillis();

        List<Future<Integer>> futures = IntStream.rangeClosed(1, 4)
                .mapToObj(i -> es.submit(() -> HeavyJob.heavyTask(i, requestName)))
                .toList();

        int sum = futures.stream()
                .mapToInt(f -> {
                    try {
                        return f.get();
                    }
                    catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .reduce(0, Integer::sum);

        long endTime = System.currentTimeMillis();
        MyLogger.log("[" + requestName + " ] END, time: " + (endTime - startTime) + "ms, sum: " + sum);
    }
}
