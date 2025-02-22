package com.java.backend.ems.controller;

import com.java.backend.ems.dto.DepartmentDto;
import com.java.backend.ems.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/departments")
@Slf4j
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/createDepartment")
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        log.info("Received request to create department with data : [{}]", departmentDto);
        DepartmentDto department = departmentService.createDepartment(departmentDto);

        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping("/getDepartmentById/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") long departmentId){
        log.info("Received request to get department with Id : [{}]", departmentId);

        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping("/getAllDepartments")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){
        log.info("Received request to get al  departments");

        List<DepartmentDto> allDepartments = departmentService.getAllDepartments();

        return ResponseEntity.ok(allDepartments);
    }

    @PutMapping("/updateDepartment/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") long departmentId, @RequestBody DepartmentDto departmentDto){
        log.info("Received request to update department with Id : [{}]", departmentId);

        DepartmentDto updatedDepartmentDto = departmentService.updateDepartment(departmentId, departmentDto);

        return ResponseEntity.ok(updatedDepartmentDto);
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") long departmentId){
        log.info("Received request to delete department with Id : [{}]", departmentId);
        try{
            departmentService.deleteDepartment(departmentId);

            return ResponseEntity.ok("Department with id " + departmentId + " deleted successfully.");
        } catch (Exception e){
            log.error("Exception while deleting department with id [{}] | Exception :: [{}]", departmentId, e.getMessage(), e);
            return ResponseEntity.ok("Could not delete department with id " + departmentId);
        }

    }
}
