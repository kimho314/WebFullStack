package com.example.responsebodyadvice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HomeDto {
    private String message;

    @Builder
    public HomeDto(String message) {
        this.message = message;
    }
}
