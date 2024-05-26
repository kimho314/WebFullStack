package com.example.bookmanager.entity;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 1000)
    private String name;

    @Column(name = "user_id", length = 100)
    private String userId;

    @Column(name = "password")
    private String password;
}
