package com.example.springcorebasic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Slf4j
@SpringBootApplication
public class SpringCoreBasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreBasicApplication.class, args);
    }


    @Component
    static class TestClass{

        public TestClass() {
            log.info("TestClass instantiated");
        }

        @PostConstruct
        void init(){
            log.info("TestClass post construct callback");
        }

        @PreDestroy
        void destroy(){
            log.info("TestClass pre destroy callback");
        }
    }

    @Component
    static class TestClass2{
        private final TestClass testClass;

        public TestClass2(TestClass testClass) {
            log.info("TestClass2 instantiated");
            this.testClass = testClass;
        }

        @PostConstruct
        void init(){
            log.info("TestClass2 post construct callback");
        }

        @PreDestroy
        void destroy(){
            log.info("TestClass2 pre destroy callback");
        }
    }
}
