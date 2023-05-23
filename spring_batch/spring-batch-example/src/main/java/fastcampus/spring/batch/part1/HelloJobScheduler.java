package fastcampus.spring.batch.part1;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HelloJobScheduler {
    private JobLauncher jobLauncher;
    private HelloConfiguration helloConfiguration;

    public HelloJobScheduler(JobLauncher jobLauncher, HelloConfiguration helloConfiguration) {
        this.jobLauncher = jobLauncher;
        this.helloConfiguration = helloConfiguration;
    }

    @Scheduled(fixedDelay = 1000)
    public void runJob() {
        try {
            // job parameter 설정
            Map<String, JobParameter> configMap = new HashMap<>();
            configMap.put("time", new JobParameter(System.currentTimeMillis()));
            JobParameters jobParameters = new JobParameters(configMap);
            jobLauncher.run(helloConfiguration.helloJob(), jobParameters);
        }
        catch (JobExecutionAlreadyRunningException |
               JobRestartException |
               JobParametersInvalidException |
               JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        }
    }
}
