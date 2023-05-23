package fastcampus.spring.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableScheduling
@EnableBatchProcessing
@SpringBootApplication
public class SpringBatchExampleApplication {

    public static void main(String[] args) {
        // System.exit과 SpringApplication.exti을 추가한 이유는 async spring batch에서는 종료가 제대로 안되는 현상이 종종 있기 때문임
//        System.exit(SpringApplication.exit(SpringApplication.run(SpringBatchExampleApplication.class, args)));
        SpringApplication.run(SpringBatchExampleApplication.class, args);
    }

    //    @Bean
    @Primary
    TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setThreadNamePrefix("batch-thread-");
        taskExecutor.initialize();
        return taskExecutor;
    }
}
