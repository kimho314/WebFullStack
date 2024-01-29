package com.example.bookmanager;

import com.example.bookmanager.dto.TraceId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TraceIdTest {

    @Test
    void traceIdCreateTest() {
        TraceId traceId = new TraceId();
        log.info("{} {}", traceId.id(), traceId.level());
    }
}
