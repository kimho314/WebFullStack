package parallelstream.forkjoin;

import parallelstream.HeavyJob;
import util.MyLogger;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Integer> {
    //    private static final int THRESHOLD = 4;
    private static final int THRESHOLD = 2;
    private final List<Integer> list;

    public SumTask(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected Integer compute() {
        if (list.size() <= THRESHOLD) {
            MyLogger.log("[처리 시작] " + list);
            int sum = list.stream()
                    .mapToInt(HeavyJob::heavyTask)
                    .sum();
            MyLogger.log("[처리 완료] " + list + " -> sum: " + sum);
            return sum;
        }
        else {
            int mid = list.size() / 2;
            List<Integer> leftList = list.subList(0, mid);
            List<Integer> rightList = list.subList(mid, list.size());
            MyLogger.log("[분할] " + list + " -> LEFT" + leftList + ", RIGHT" + rightList);

            SumTask leftTask = new SumTask(leftList);
            SumTask rightTask = new SumTask(rightList);

            // 왼쪽 작업은 다른 스레드에서 처리
            leftTask.fork();
            //오른쪽 작업은 현재 스레드에서 처리
            Integer rightSum = rightTask.compute();
            Integer leftSum = leftTask.join();
            int sum = leftSum + rightSum;
            MyLogger.log("LEFT[ " + leftSum + "] + RIGHT[" + rightSum + "] -> sum: " + sum);
            return sum;
        }
    }
}
