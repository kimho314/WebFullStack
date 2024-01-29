package com.example.bookmanager.dto;

public record TraceStatus(TraceId traceId, Long startTimeMs, String message) {
}
