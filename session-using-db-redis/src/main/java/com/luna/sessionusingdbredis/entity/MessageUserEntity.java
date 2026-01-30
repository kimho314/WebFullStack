package com.luna.sessionusingdbredis.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "message_user")
public class MessageUserEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name ="password", nullable = false)
    private String password;


    public MessageUserEntity() {
    }

    public MessageUserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        MessageUserEntity that = (MessageUserEntity) o;
        return Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    @Override
    public String toString() {
        return "MessageUserEntity{userId=%d, username='%s', createAt=%s, updatedAt=%s}"
                .formatted(userId, username, getCreateAt(), getUpdatedAt());
    }
}
