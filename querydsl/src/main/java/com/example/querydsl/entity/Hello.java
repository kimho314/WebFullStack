package com.example.querydsl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.websocket.server.ServerEndpoint;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hello {
    @Id
    @GeneratedValue
    private Long id;
}
