package com.example.bookmanager.dto;

import java.util.UUID;

public record TraceId(String id, int level) {
    public TraceId() {
        this(createId(), 0);
    }

    private static String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }
}
