package com.java.backend.ems.service;

import com.java.backend.ems.dto.DepartmentDto;
import com.java.backend.ems.dto.EmployeeDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(long departmentId);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartment(long departmentId, DepartmentDto departmentDto);
    void deleteDepartment(long departmentId);
}
