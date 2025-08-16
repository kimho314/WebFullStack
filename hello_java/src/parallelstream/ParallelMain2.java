package parallelstream;

import util.MyLogger;

public class ParallelMain2 {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        SumTask sumTask1 = new SumTask(1, 4);
        SumTask sumTask2 = new SumTask(5, 8);

        Thread thread1 = new Thread(sumTask1);
        Thread thread2 = new Thread(sumTask2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        int sum = sumTask1.result + sumTask2.result;

        long endTime = System.currentTimeMillis();
        MyLogger.log("time: " + (endTime - startTime) + "ms, sum: " + sum);
    }

    private static class SumTask implements Runnable {
        private int startValue;
        private int endValue;
        private int result;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public void run() {
            MyLogger.log("작업 싲가");
            int sum = 0;
            for (int i = startValue; i <= endValue; i++) {
                int calculated = HeavyJob.heavyTask(i);
                sum += calculated;
            }
            result = sum;
            MyLogger.log("작업 완료 result = " + result);
        }
    }
}
