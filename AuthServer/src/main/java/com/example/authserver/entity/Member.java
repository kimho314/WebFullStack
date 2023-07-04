package com.example.authserver.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@Table(name = "member")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(length = 11)
    private String phoneNumber;

    @Column(length = 320)
    private String email;

    @Column(length = 12, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;
}
