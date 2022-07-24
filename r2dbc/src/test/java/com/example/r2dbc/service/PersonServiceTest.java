package com.example.r2dbc.service;

import com.example.r2dbc.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void saveTest() {
        var person = Person.builder()
                .id(0L)
                .firstname("hoseop")
                .lastname("kim")
                .build();
        personService.save(person);
    }
}