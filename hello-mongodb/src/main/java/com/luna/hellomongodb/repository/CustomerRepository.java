package com.luna.hellomongodb.repository;

import com.luna.hellomongodb.domain.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<CustomerEntity, String> {
    Optional<CustomerEntity> findByFirstName(String firstName);
    List<CustomerEntity> findByLastName(String lastName);
}
