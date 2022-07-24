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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ActiveProfiles(profiles = "postgresql")
@SpringBatchTest // 해당 annotation 을 부텽줘야 @JobScope 이 Test 에서 정상적으로 동작함
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserRankBatchConfiguration.class, TestConfiguration.class, SaveUserTasklet.class})
class UserRankBatchConfigurationTest {
    private static final Long INCREMENT = 100000L;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @AfterEach
    void afterAll() {
        userRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    public void saveUserStepTest() {
        //given
        final String testStep = "saveUserStep";

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchStep(testStep);

        //then
        final Long expectedUserCount = 5L;
        final Long userCount = userRepository.count();

        Assertions.assertEquals(expectedUserCount, userCount);
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
        final Long expectedUserCount = 5L;
        Assertions.assertEquals(expectedUserCount,
                jobExecution.getStepExecutions().stream()
                        .filter(stepExecution -> stepExecution.getStepName().equals("userLevelUpStep"))
                        .mapToInt(StepExecution::getWriteCount)
                        .sum());

        List<User> expectedUsers = getUsers();

        List<User> users = userRepository.findAll();
        for (int i = 0; i < users.size(); i++) {
            Assertions.assertEquals(expectedUsers.get(i).getName(), users.get(i).getName());
            Assertions.assertEquals(expectedUsers.get(i).getAge(), users.get(i).getAge());
        }
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .name("test name" + (i + 1))
                    .age((long) i + 1)
                    .localDateTime(LocalDateTime.now())
                    .build();
            Order order = Order.builder()
                    .score(INCREMENT * (i + 1))
                    .updateDateTime(LocalDateTime.now())
                    .build();
            user.addOrder(order);

            users.add(user);
        }

        return users;
    }

}