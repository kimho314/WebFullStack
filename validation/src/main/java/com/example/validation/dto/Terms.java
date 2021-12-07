package com.example.validation.dto;

import lombok.*;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class Terms {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        @NotNull
        @Valid
        @Builder.Default
        private List<Term> terms = new ArrayList<>();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{

        private String responseMessage;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Term{
        @Digits(integer = 3, fraction = 0)
//        @Max(value = 3, message = "id's length must be 3 or less")
        private Integer id;
    }
}
