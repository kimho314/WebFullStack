package fastcampus.spring.batch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Configuration
public class HelloConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final HelloStepListener helloStepListener;

    public HelloConfiguration(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory,
                              HelloStepListener helloStepListener) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.helloStepListener = helloStepListener;
    }

    @PostConstruct
    void init() {
        log.info("================[HelloConfiguration init]================");
    }

    @PreDestroy
    void destroy() {
        log.info("================[HelloConfiguration destroy]================");
    }

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer()) // RunIdIncrementer() 항상 새로운 run id 생성하게 해줌
                .start(this.helloStep())
                .build();
    }

    /**
     * tasklet : 덩치가 작고 한번에 실행하는 작업에 적합
     * chunk : 큰 작업을 나눠서 실행할 때 적합
     *
     * @return
     */
    @Bean
    public Step helloStep() {
        return stepBuilderFactory.get("helloStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("hello spring batch");
                    Object key = contribution.getStepExecution().getExecutionContext().get("key");
                    log.info(String.valueOf(key));
                    contribution.getStepExecution().getExecutionContext().put("key", "pong");
                    return RepeatStatus.FINISHED;
                })
                .listener(helloStepListener)
                .build();
    }


}
