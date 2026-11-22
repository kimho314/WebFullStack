package com.luna.oracleautonomousedatabasedemo.domain.dtos;

import com.luna.oracleautonomousedatabasedemo.domain.entity.Employee;

public record CreateEmployeeReqDto(String name, Long salary, Long commission) {

    public Employee toEntity() {
        return new Employee(null, this.name, this.salary, this.commission);
    }

    public static CreateEmployeeReqDto toDto(Employee employee) {
        return new CreateEmployeeReqDto(employee.name(), employee.salary(), employee.commission());
    }
}
