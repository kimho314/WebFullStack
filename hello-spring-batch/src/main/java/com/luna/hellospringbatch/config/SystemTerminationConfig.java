package com.luna.hellospringbatch.config;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

//@Import(BatchConfig.class) // in spring boot automatically search the configuration and inject it
@Configuration
public class SystemTerminationConfig {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private AtomicInteger processesKilled = new AtomicInteger(0);
    private final int TERMINATION_TARGET = 5;

    public SystemTerminationConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job systemTerminationSimulationJob() {
        return new JobBuilder("systemTerminationSimulationJob", jobRepository)
            .start(enterWorldStep())
            .next(meetNPCStep())
            .next(defeatProcessStep())
            .next(completeQuestStep())
            .build();
    }

    @Bean
    public Step enterWorldStep() {
        return new StepBuilder("enterWorldStep", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                IO.println("System Termination 시뮬레이션 세계에 접속했습니다!");
                return RepeatStatus.FINISHED;
            }, transactionManager)
            .build();
    }

    @Bean
    public Step meetNPCStep() {
        return new StepBuilder("meetNPCStep", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                IO.println("시스템 관리자 NPC를 만났습니다.");
                IO.println("첫 번째 미션: 좀비 프로세스 " + TERMINATION_TARGET + "개 처형하기");
                return RepeatStatus.FINISHED;
            }, transactionManager)
            .build();
    }

    @Bean
    public Step defeatProcessStep() {
        return new StepBuilder("defeatProcessStep", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                int terminated = processesKilled.incrementAndGet();
                IO.println("좀비 프로세스 처형 완료! (현재 " + terminated + "/" + TERMINATION_TARGET + ")");
                if (terminated < TERMINATION_TARGET) {
                    return RepeatStatus.CONTINUABLE;
                } else {
                    return RepeatStatus.FINISHED;
                }
            }, transactionManager)
            .build();
    }

    @Bean
    public Step completeQuestStep() {
        return new StepBuilder("completeQuestStep", jobRepository)
            .tasklet((contribution, chunkContext) -> {
                IO.println("미션 완료! 좀비 프로세스 " + TERMINATION_TARGET + "개 처형 성공!");
                IO.println("보상: kill -9 권한 획득, 시스템 제어 레벨 1 달성");
                return RepeatStatus.FINISHED;
            }, transactionManager)
            .build();
    }
}
