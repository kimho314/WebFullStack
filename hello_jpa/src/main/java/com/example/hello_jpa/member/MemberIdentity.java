package com.example.hello_jpa.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_identity")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    public MemberIdentity(String name, int age) {
        this(null, name, age);
    }

    public MemberIdentity(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void changeName(String name) {
        this.name = name;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof MemberIdentity memberIdentity)) {
            return false;
        }

        return getId().equals(memberIdentity.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
