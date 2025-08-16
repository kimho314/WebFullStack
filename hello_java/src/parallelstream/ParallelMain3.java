package parallelstream;

import util.MyLogger;

import java.util.concurrent.*;

public class ParallelMain3 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (ExecutorService es = Executors.newFixedThreadPool(2);) {
            long startTime = System.currentTimeMillis();

            SumTask sumTask1 = new SumTask(1, 4);
            SumTask sumTask2 = new SumTask(5, 8);

            Future<Integer> future1 = es.submit(sumTask1);
            Future<Integer> future2 = es.submit(sumTask2);

            int sum = future1.get() + future2.get();

            long endTime = System.currentTimeMillis();
            MyLogger.log("time: " + (endTime - startTime) + "ms, sum: " + sum);
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    private static class SumTask implements Callable<Integer> {
        private int startValue;
        private int endValue;
        private int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() {
            MyLogger.log("작업 싲가");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyTask(i);
                sum += calculated;
            }
            result = sum;
            MyLogger.log("작업 완료 result = " + result);
            return result;
        }
    }
}
