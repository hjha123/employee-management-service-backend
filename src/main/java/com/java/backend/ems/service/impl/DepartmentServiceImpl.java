package com.java.backend.ems.service.impl;

import com.java.backend.ems.dto.DepartmentDto;
import com.java.backend.ems.dto.EmployeeDto;
import com.java.backend.ems.entity.Department;
import com.java.backend.ems.exception.ResourceNotFoundException;
import com.java.backend.ems.repository.DepartmentRepository;
import com.java.backend.ems.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private DepartmentRepository departmentRepository;

    private ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = modelMapper.map(departmentDto, Department.class);

        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentDto.class);
    }

    @Override
    public DepartmentDto getDepartmentById(long departmentId) {
        DepartmentDto departmentDto = null;
        try {
            Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                    new ResourceNotFoundException("Department doesn't found with the given Id " + departmentId));
            departmentDto = modelMapper.map(department, DepartmentDto.class);
            log.info("Department details: [{}]", departmentDto);
        } catch (Exception e) {
            log.error("Exception while fetching the department with given Id : [{}] | Exception : [{}]", departmentId,
                    e.getMessage(), e);
        }
        return departmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentDto> departments = null;
        try{
            List<Department> allDepartments = departmentRepository.findAll();
            departments = allDepartments.stream().map(department -> modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
        } catch (Exception e){
            log.error("Exception while fetching all departments. | Exception : [{}]", e.getMessage(), e);
        }
        return departments;
    }

    @Override
    public DepartmentDto updateDepartment(long departmentId, DepartmentDto departmentDto) {
        DepartmentDto updatedDepartmentDto = null;
        try{
            Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                    new ResourceNotFoundException("Department doesn't found with the given Id " + departmentId));
            department.setDepartmentName(departmentDto.getDepartmentName());
            department.setDepartmentDescription(departmentDto.getDepartmentDescription());

            Department updatedDepartment = departmentRepository.save(department);
            updatedDepartmentDto = modelMapper.map(updatedDepartment, DepartmentDto.class);

        } catch (Exception e){
            log.error("Exception while updating department for Id [{}] | Exception : [{}]", departmentId, e.getMessage(), e);
        }
        return updatedDepartmentDto;
    }

    @Override
    public void deleteDepartment(long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() ->
                new ResourceNotFoundException("Department doesn't found with the given Id " + departmentId));
        departmentRepository.delete(department);
    }

}
