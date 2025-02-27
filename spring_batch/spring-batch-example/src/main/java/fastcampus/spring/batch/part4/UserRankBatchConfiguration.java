package fastcampus.spring.batch.part4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * user rank system : NORMAL, SILVER, COLD, VIP
 * user rank scores
 * - normal : 0 ~ 200,000
 * - silver : over 200,000
 * - gold : over 300,000
 * - vip : over 500,000
 * - no degrading user rank
 * consists of two steps
 * - saveUserStep : save User info
 * - userLevelUpStep : promote User rank
 * JobExecutionListener.afterJob : print out log "총 데이터 처리 {}건, 처리 시간 : {} millis"
 * User의 totalAmount를 Orders Entity로 변경
 * - User 1 ... N Order
 * 주문 총 금액은 Order 기준으로 합산
 * '-date=2020-11' JobParameters 사용
 * - 주문 금액 집께는 orderStatisticsStep으로 생성
 * - '2020년_11월_주문_금액.csv' 파일은 2020년 11월 1일 ~ 11월 30일 주문 통계 내역
 * - date 파라미터가 없는 경우, orderStatisticsStep 은 실행하지 않는다.
 */
@Slf4j
@Configuration
public class UserRankBatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final SaveUserTasklet saveUserTasklet;

    public UserRankBatchConfiguration(JobBuilderFactory jobBuilderFactory,
                                      StepBuilderFactory stepBuilderFactory,
                                      EntityManagerFactory entityManagerFactory,
                                      SaveUserTasklet saveUserTasklet) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.saveUserTasklet = saveUserTasklet;
    }

    @Bean
    public Job userRankJob() throws Exception {
        return jobBuilderFactory.get("userRankJob")
                .incrementer(new RunIdIncrementer())
                .start(this.saveUserStep())
                .next(this.userLevelUpStep(null))
                .listener(new UserRankJobExecutionListener())
                .build();
    }

    @Bean
    @JobScope
    public Step userLevelUpStep(@Value("#{jobParameters[date]}") String date) throws Exception {
        return stepBuilderFactory.get("userLevelUpStep")
                .<User, User>chunk(10)
                .reader(jpaUserReader())
                .processor(userRankPromotionProcessor(date))
                .writer(jpaUserRankWriter())
                .build();
    }

    private ItemWriter<? super User> jpaUserRankWriter() throws Exception {
        JpaItemWriter<User> itemWriter = new JpaItemWriterBuilder<User>()
                .entityManagerFactory(entityManagerFactory)
                .build();
        itemWriter.afterPropertiesSet();
        return itemWriter;
    }

    private ItemProcessor<? super User, ? extends User> userRankPromotionProcessor(String date) {
        String[] splitDate = date.split("-");
        String year = splitDate[0];
        String month = splitDate[1];
        return item -> {
            Rank rank = Rank.findByScore(item.getTotalScore(year, month));
            return item.updateRank(rank);
        };
    }

    private ItemReader<? extends User> jpaUserReader() throws Exception {
        JpaPagingItemReader<User> itemReader = new JpaPagingItemReaderBuilder<User>()
                .name("userJpaCursorReader")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select u from User u order by id asc")
                .pageSize(10) // pagingItemReader 의 pageSize 는 chunk size와 동일하게 맞춘다
                .build();
        itemReader.afterPropertiesSet();
        return itemReader;
    }

    @Bean
    @JobScope
    public Step saveUserStep() throws Exception {
        return stepBuilderFactory.get("saveUserStep")
                .tasklet(saveUserTasklet)
                .build();
    }
}
