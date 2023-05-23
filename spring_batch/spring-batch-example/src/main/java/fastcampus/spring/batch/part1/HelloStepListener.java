package fastcampus.spring.batch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloStepListener {
    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.info("===================[Before Hello Step]===================");
        stepExecution.getExecutionContext().put("key", "ping");
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        log.info("===================[After Hello Step]===================");
        Object key = stepExecution.getExecutionContext().get("key");
        log.info(String.valueOf(key));
    }
}
