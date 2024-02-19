package com.example.bookmanager.component;

import com.example.bookmanager.dto.TraceId;
import com.example.bookmanager.dto.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV2 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.id(), addSpace(START_PREFIX, traceId.level()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        TraceId nextId = beforeTraceId.createNextId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", nextId.id(), addSpace(START_PREFIX, nextId.level()), message);
        return new TraceStatus(nextId, startTimeMs, message);
    }

    public void end(TraceStatus traceStatus) {
        complete(traceStatus, null);
    }

    public void exception(TraceStatus traceStatus, Exception e) {
        complete(traceStatus, e);
    }

    private void complete(TraceStatus traceStatus, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - traceStatus.startTimeMs();
        TraceId traceId = traceStatus.traceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.id(), addSpace(COMPLETE_PREFIX, traceId.level()), traceStatus.message(), resultTimeMs);
        }
        else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.id(), addSpace(EX_PREFIX, traceId.level()), traceStatus.message(), resultTimeMs, e.toString());
        }
    }

    private Object addSpace(String startPrefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + startPrefix : "|     ");
        }
        return sb.toString();
    }
}
