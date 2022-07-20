package fastcampus.spring.batch.part3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;

@Slf4j
public class UserRankJobExecutionListener implements JobExecutionListener {
    private static long jobCreateEpochMilli;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("# beforeJob");
        long startEpochMilli = jobExecution.getCreateTime().toInstant().toEpochMilli();
        jobCreateEpochMilli = startEpochMilli;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("# afterJob");
        long endEpochMilli = jobExecution.getEndTime().toInstant().toEpochMilli();
        long duration = endEpochMilli - jobCreateEpochMilli;
        int sum = jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum();
        log.info("총 데이터 처리 {}건, 처리 시간 : {} millis", sum, duration);
    }
}
