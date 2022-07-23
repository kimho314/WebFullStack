package fastcampus.spring.batch.part4;

import fastcampus.spring.batch.TestConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, JobParameter> map = new HashMap<>();
        map.put("date", new JobParameter("2022-07"));
        JobParameters jobParameters = new JobParameters(map);

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(new JobParametersBuilder()
                .addJobParameters(jobParameters)
                .toJobParameters());

        //then
        final Long expectedUserCount = 3L;
        Assertions.assertEquals(expectedUserCount, jobExecution.getStepExecutions().stream()
                .filter(stepExecution -> stepExecution.getStepName().equals("userLevelUpStep"))
                .mapToInt(StepExecution::getWriteCount)
                .sum());

        User user1 = User.builder()
                .name("김호섭")
                .age(34L)
                .localDateTime(LocalDateTime.now())
                .build();
        user1.updateRank(Rank.GOLD);
        user1.addOrder(Order.builder()
                .score(300000L)
                .updateDateTime(LocalDateTime.now())
                .build());

        User user2 = User.builder()
                .name("닐")
                .age(30L)
                .localDateTime(LocalDateTime.now())
                .build();
        user2.updateRank(Rank.VIP);
        user2.addOrder(Order.builder()
                .score(500000L)
                .updateDateTime(LocalDateTime.now())
                .build());

        User user3 = User.builder()
                .name("김민섭")
                .age(32L)
                .localDateTime(LocalDateTime.now())
                .build();
        user3.addOrder(Order.builder()
                .score(100000L)
                .updateDateTime(LocalDateTime.now())
                .build());
        List<User> expectedUsers = Arrays.asList(user1, user2, user3);

        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(expectedUsers.get(i).getName(), users.get(i).getName());
            Assertions.assertEquals(expectedUsers.get(i).getAge(), users.get(i).getAge());
            Assertions.assertEquals(expectedUsers.get(i).getRank(), users.get(i).getRank());
        }
    }

}