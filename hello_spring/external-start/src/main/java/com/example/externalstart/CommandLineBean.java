package com.example.externalstart;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommandLineBean {

    private final ApplicationArguments applicationArguments;

    public CommandLineBean(ApplicationArguments applicationArguments) {
        this.applicationArguments = applicationArguments;
    }

    @PostConstruct
    public void init() {
        log.info("source {}", List.of(applicationArguments.getSourceArgs()));
        log.info("optionNames {}", applicationArguments.getOptionNames());
        Set<String> optionNames = applicationArguments.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option args {}={}", optionName, applicationArguments.getOptionValues(optionName));
        }
    }
}
