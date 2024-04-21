package com.example.lunit.common.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public enum Role {
    CLIENT,
    ADMIN;

    public static SimpleGrantedAuthority findByName(String name) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(name))
                .map(it -> mappedToAuthority(it))
                .map(it -> new SimpleGrantedAuthority(it))
                .findFirst()
                .orElse(null);
    }

    public static String mappedToAuthority(Role role) {
        return "ROLE_" + role.name();
    }

    public static Role mappedToRole(String authority) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(authority.substring(5)))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
