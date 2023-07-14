package com.example.authserver.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Table(name = "member")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 11)
    private String phoneNumber;

    @Column(length = 320)
    private String email;

    @Column(length = 12, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    private List<Token> tokens = new ArrayList<>();
}
