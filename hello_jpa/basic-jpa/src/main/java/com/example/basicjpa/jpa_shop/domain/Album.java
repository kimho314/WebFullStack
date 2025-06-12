package com.example.basicjpa.jpa_shop.domain;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Album extends Item {

    private String artist;
}
