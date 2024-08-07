package com.example.authserver.domain.member.entity;

import com.example.authserver.core.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String userId;

    @Column(length = 2000)
    private String password;

    @Column(length = 300)
    private String name;

    @Column(length = 100)
    private String phoneNumber;

    @Column(length = 320)
    private String email;

    @Builder.Default
    private Integer enabled = 1;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Authority> authorities = new ArrayList<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens = new ArrayList<>();

    public List<Role> getRoles() {
        return this.authorities.stream()
                .map(it -> it.getRole())
                .toList();
    }

    public boolean isEnabled() {
        return this.enabled == 1;
    }
}
