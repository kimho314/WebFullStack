package com.luna.oracleautonomousedatabasedemo.repository;

import com.luna.oracleautonomousedatabasedemo.domain.entity.Employee;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ListCrudRepository<Employee, Long> {

}
