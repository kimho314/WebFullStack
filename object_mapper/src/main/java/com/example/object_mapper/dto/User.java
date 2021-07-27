package com.example.object_mapper.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private String name;
    private int age;
    private List<Car> cars;
}
