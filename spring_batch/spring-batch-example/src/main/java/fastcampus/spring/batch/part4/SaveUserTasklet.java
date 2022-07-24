package fastcampus.spring.batch.part4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SaveUserTasklet implements Tasklet {
    private static final Long INCREMENT = 100000L;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public SaveUserTasklet(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<User> users = getUsers();
        userRepository.saveAll(users);

        List<Order> orders = users.stream()
                .map(User::getOrders)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        orderRepository.saveAll(orders);

        return RepeatStatus.FINISHED;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var user = User.builder()
                    .name("test name" + (i + 1))
                    .age((long) i + 1)
                    .localDateTime(LocalDateTime.now())
                    .build();
            var order = Order.builder()
                    .score(INCREMENT * (i + 1))
                    .updateDateTime(LocalDateTime.now())
                    .build();
            user.addOrder(order);

            users.add(user);
        }

        return users;
    }
}
