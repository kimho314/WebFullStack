package com.luna.oracleautonomousedatabasedemo.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.luna.oracleautonomousedatabasedemo.domain.dtos.CreateEmployeeReqDto;
import com.luna.oracleautonomousedatabasedemo.domain.entity.Employee;
import com.luna.oracleautonomousedatabasedemo.repository.EmployeeRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class EmployeeServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class);
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    @Transactional
    void createEmployee() {
        employeeService.createEmployee(new CreateEmployeeReqDto("John", 10000L, 1000L));

        List<Employee> employees = employeeRepository.findAll();
        assertThat(employees).hasSize(1);
    }
}