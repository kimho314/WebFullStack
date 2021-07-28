package com.example.exception.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ErrorResponse {
    private String statusCode;
    private String requestUrl;
    private String code;
    private String message;
    private String resultCode;

    private List<Error> errors = new ArrayList<>();
}
