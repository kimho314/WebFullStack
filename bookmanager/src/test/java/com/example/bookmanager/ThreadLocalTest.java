package com.example.bookmanager;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ThreadLocalTest {

    static class Context {
        private String userName;

        public Context(String userName) {
            this.userName = userName;
        }
    }

    static class UserRepository {
        public String getUserNameByUserId(Integer userId) {
            return String.valueOf(userId);
        }
    }

    static class SharedMapWithUserContext implements Runnable {
        public static Map<Integer, Context> userContextPerUserId = new ConcurrentHashMap<>();
        public static ThreadLocal<Context> userContext = new ThreadLocal<>();
        private final Integer userId;
        private final UserRepository userRepository = new UserRepository();

        @Override
        public void run() {
            String userName = userRepository.getUserNameByUserId(userId);
            userContext.set(new Context(userName));
            log.info("thread {} context for give userId : {} is : {}", Thread.currentThread().getName(), userId, userContext.get().userName);
//            userContextPerUserId.put(userId, new Context(userName));
//            log.info("userId : {}, userName : {}", userId, userName);
        }

        public SharedMapWithUserContext(Integer userId) {
            this.userId = userId;
        }
    }

    @Test
    void threadLocalTest() {
        SharedMapWithUserContext firstUser = new SharedMapWithUserContext(1);
        SharedMapWithUserContext secondUser = new SharedMapWithUserContext(2);

        new Thread(firstUser).start();
        new Thread(secondUser).start();
//        SharedMapWithUserContext.userContextPerUserId.forEach((integer, context) -> log.info("{} : {}", integer, context.userName));
//        Assertions.assertEquals(2, SharedMapWithUserContext.userContextPerUserId.size());
    }
}
