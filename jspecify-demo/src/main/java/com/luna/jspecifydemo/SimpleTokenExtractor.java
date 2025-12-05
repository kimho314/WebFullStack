package com.luna.jspecifydemo;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SimpleTokenExtractor implements TokenExtractor{
    @Override
    public @Nullable String extractToken(String input) {
        return (input.contains("token") ? "token" : null);
    }
}
