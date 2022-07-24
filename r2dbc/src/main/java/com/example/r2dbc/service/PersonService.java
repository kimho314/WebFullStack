package com.example.r2dbc.service;

import com.example.r2dbc.entity.Person;
import com.example.r2dbc.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person _person) {
        var personMono = personRepository.save(_person);
        var person = personMono.block();
        if (Objects.isNull(person)) {
            throw new NullPointerException();
        }
    }
}
