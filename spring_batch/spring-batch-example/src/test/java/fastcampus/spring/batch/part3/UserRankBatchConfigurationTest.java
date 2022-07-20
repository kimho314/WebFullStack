package fastcampus.spring.batch.part3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@ActiveProfiles(profiles = "postgresql")
@SpringBatchTest // 해당 annotation 을 부텽줘야 @JobScope 이 Test 에서 정상적으로 동작함
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserRankBatchConfiguration.class, TestConfiguration.class})
class UserRankBatchConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void afterAll() {
        userRepository.deleteAll();
    }

    @Test
    public void saveUserStepTest() {
        //given
        final String testStep = "saveUserStep";

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep(testStep);

        //then
        final Long expectedUserCount = 3L;
        final Long expectJpaCount = userRepository.count();

        int sum = jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum();
        Assertions.assertEquals(expectedUserCount, sum);
        Assertions.assertEquals(expectJpaCount, sum);
    }

    @Test
    public void userLevelUpStepTest() throws Exception {
        //given
        final String userLevelUpStep = "userLevelUpStep";

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParametersBuilder().toJobParameters());

        //then
        final Long expectedUserCount = 3L;
        Assertions.assertEquals(expectedUserCount, jobExecution.getStepExecutions().stream()
                .filter(stepExecution -> stepExecution.getStepName().equals("userLevelUpStep"))
                .mapToInt(StepExecution::getWriteCount)
                .sum());

        User user1 = User.builder()
                .name("김호섭")
                .age(34L)
                .score(300000L)
                .rank(Rank.GOLD)
                .build();
        User user2 = User.builder()
                .name("닐")
                .age(30L)
                .score(500000L)
                .rank(Rank.VIP)
                .build();
        User user3 = User.builder()
                .name("김민섭")
                .age(32L)
                .score(100000L)
                .build();
        List<User> expectedUsers = Arrays.asList(user1, user2, user3);

        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(expectedUsers.get(i), users.get(i));
        }
    }

}