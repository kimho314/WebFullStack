package com.example.responsebodyadvice.constants;

import java.util.Arrays;
import java.util.Objects;

public enum ResponseCode {
    COMMON_OK(0),
    COMMON_BAD_REQUEST(1),
    COMMON_INVALID_PARAMETER(2),
    COMMON_INTERNAL_SERVER_ERROR(3),
    ;

    private final Integer value;

    ResponseCode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static ResponseCode of(Integer value) {
        return Arrays.stream(ResponseCode.values())
                .filter(responseCode -> Objects.equals(responseCode.getValue(), value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
