package com.example.bookmanager;

import com.example.bookmanager.component.HelloTraceV2;
import com.example.bookmanager.dto.TraceStatus;
import org.junit.jupiter.api.Test;

public class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.traceId(), "hello2");
        trace.end(status1);
        trace.end(status2);
    }

    @Test
    void begin_exception_level2() {
        HelloTraceV2 trace = new HelloTraceV2();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.traceId(), "hello2");
        trace.exception(status1, new IllegalStateException("exception occurred"));
        trace.exception(status2, new IllegalStateException("exception occurred"));
    }
}
