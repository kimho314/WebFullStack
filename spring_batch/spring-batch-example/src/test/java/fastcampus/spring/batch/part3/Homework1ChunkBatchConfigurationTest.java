package fastcampus.spring.batch.part3;

import fastcampus.spring.batch.TestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBatchTest // 해당 annotation 을 부텽줘야 @JobScope 이 Test 에서 정상적으로 동작함
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfiguration.class, Homework1ChunkBatchConfiguration.class})
class Homework1ChunkBatchConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    public void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    public void test_steo() {
        //given
        final Integer expectedPersonCount = 3;

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("homework1Step");

        //then
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                        .mapToInt(StepExecution::getWriteCount)
                        .sum())
                .isEqualTo(personRepository.count())
                .isEqualTo(expectedPersonCount);
    }

    @Test
    public void test_allow_duplicate() throws Exception {
        //given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "false")
                .toJobParameters();
        final Integer expectedPersonCount = 3;

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                        .mapToInt(StepExecution::getWriteCount)
                        .sum())
                .isEqualTo(personRepository.count())
                .isEqualTo(expectedPersonCount);
    }

    @Test
    public void test_not_allow_duplicate() throws Exception {
        //given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "true")
                .toJobParameters();
        final Integer expectedPersonCount = 100;

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                        .mapToInt(StepExecution::getWriteCount)
                        .sum())
                .isEqualTo(personRepository.count())
                .isEqualTo(expectedPersonCount);
    }
}