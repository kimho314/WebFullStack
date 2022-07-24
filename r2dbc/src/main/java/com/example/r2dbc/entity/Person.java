package com.example.r2dbc.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@NoArgsConstructor
public class Person implements Persistable<Long> {
    @Id
    private Long id;
    private String firstname;
    private String lastname;

    @Builder
    public Person(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Transient
    @Override
    public boolean isNew() {
        return this.id == 0L;
    }
}
