package com.luna.oracleautonomousedatabasedemo.service;

import com.luna.oracleautonomousedatabasedemo.domain.dtos.CreateEmployeeReqDto;
import com.luna.oracleautonomousedatabasedemo.domain.entity.Employee;
import com.luna.oracleautonomousedatabasedemo.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void createEmployee(CreateEmployeeReqDto request) {
        Employee employee = request.toEntity();
        Employee saved = employeeRepository.save(employee);
        log.info("Employee created: {}", saved);
    }

    @Transactional(readOnly = true)
    public CreateEmployeeReqDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        return CreateEmployeeReqDto.toDto(employee);
    }
}
