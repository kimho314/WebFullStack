package com.example.authserver.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "member")
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    @Column(length = 2000)
    private String password;

    @Column(length = 300)
    private String name;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 320)
    private String email;
}
