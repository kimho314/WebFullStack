package com.luna.hellomongodb;

import com.luna.hellomongodb.domain.CustomerEntity;
import com.luna.hellomongodb.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloMongodbApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;
    public static void main(String[] args) {
        SpringApplication.run(HelloMongodbApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.deleteAll();

        // save a couple of customers
        customerRepository.save(new CustomerEntity("Alice", "Smith"));
        customerRepository.save(new CustomerEntity("Bob", "Smith"));

        // fetch all customers
        IO.println("Customers found with .findAll():");
        IO.println("-------------------------------");
        for (CustomerEntity customer : customerRepository.findAll()) {
            IO.println(customer);
        }
        IO.println();

        // fetch an individual customer
        IO.println("Customer found with findByFirstName('Alice'):");
        IO.println("--------------------------------");
        IO.println(customerRepository.findByFirstName("Alice"));

        IO.println("Customers found with findByLastName('Smith'):");
        IO.println("--------------------------------");
        for (CustomerEntity customer : customerRepository.findByLastName("Smith")) {
            IO.println(customer);
        }
    }
}
