package com.java.backend.ems.controller;

import com.java.backend.ems.dto.EmployeeDto;
import com.java.backend.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/addEmployee")
    ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/getEmployee/{id}")
    ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);

        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/getEmployees")
    ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();

        return ResponseEntity.ok(allEmployees);
    }

    @PutMapping("/updateEmployee/{id}")
    ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") long employeeId, @RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmpDto = employeeService.updateEmployee(employeeId, employeeDto);

        return ResponseEntity.ok(updatedEmpDto);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId){
        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
    }

    @GetMapping("/getEmployeeByDeptId/{id}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByDeptId(@PathVariable("id") long departmentId){
        List<EmployeeDto> employeesByDepartmentId = employeeService.getEmployeesByDepartmentId(departmentId);

        return ResponseEntity.ok(employeesByDepartmentId);
    }
}
