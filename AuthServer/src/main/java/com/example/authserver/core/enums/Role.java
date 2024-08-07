package com.example.authserver.core.enums;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN;

    public String toSecurityRole() {
        return this.name().substring(5);
    }
}
