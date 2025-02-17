package com.java.backend.ems.service;

import com.java.backend.ems.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
    EmployeeDto getEmployeeById(long employeeId);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto updateEmployee(long employeeId, EmployeeDto employeeDto);
    String deleteEmployee(long employeeId);
}
