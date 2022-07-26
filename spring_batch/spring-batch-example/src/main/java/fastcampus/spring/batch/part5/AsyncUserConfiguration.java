package fastcampus.spring.batch.part5;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Configuration
public class AsyncUserConfiguration {
    private final String JOB_NAME = "asyncUserJob";
    private final int CHUNK = 1000;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;
    private final TaskExecutor taskExecutor;

    public AsyncUserConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                                  EntityManagerFactory entityManagerFactory, DataSource dataSource,
                                  TaskExecutor taskExecutor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.dataSource = dataSource;
        this.taskExecutor = taskExecutor;
    }

    @Bean(JOB_NAME)
    public Job userJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .start(saveUserStep())
                .build();
    }

    @JobScope
    @Bean(JOB_NAME + "_save_user_step")
    public Step saveUserStep() {
        return stepBuilderFactory.get(JOB_NAME + "_save_user_step")
                .<User2, Future<User2>>chunk(CHUNK)
                .reader(getUsers())
                .processor(updateUserDateTime())
                .writer(saveUser())
                .build();
    }

    private AsyncItemWriter<User2> saveUser() {
        JpaItemWriter<User2> itemWriter = new JpaItemWriterBuilder<User2>()
                .entityManagerFactory(entityManagerFactory)
                .usePersist(true)
                .build();
        AsyncItemWriter<User2> asyncItemWriter = new AsyncItemWriter<>();
        asyncItemWriter.setDelegate(itemWriter);

        return asyncItemWriter;
    }

    private AsyncItemProcessor<User2, User2> updateUserDateTime() {
        ItemProcessor<User2, User2> itemProcessor = user2 -> {
            user2.setUpdateDateTime(LocalDateTime.now());
            return user2;
        };

        AsyncItemProcessor<User2, User2> asyncItemProcessor = new AsyncItemProcessor<>();
        asyncItemProcessor.setDelegate(itemProcessor);
        asyncItemProcessor.setTaskExecutor(this.taskExecutor);

        return asyncItemProcessor;
    }

    private ListItemReader<User2> getUsers() {
        var users = IntStream.range(0, CHUNK)
                .mapToObj(i -> User2.builder()
                        .name("name" + i)
                        .age((long) i)
                        .build())
                .collect(Collectors.toList());
        return new ListItemReader<>(users);
    }
}
