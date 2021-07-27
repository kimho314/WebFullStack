package com.example.object_mapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Car {
    private String name;
    @JsonProperty("car_number")
    private String carNumber;
    @JsonProperty("TYPE")
    private String type;
}
