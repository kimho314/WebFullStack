package com.sp.fc;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {

    private String name;

    @Builder
    public Person(String name) {
        this.name = name;
    }
}
