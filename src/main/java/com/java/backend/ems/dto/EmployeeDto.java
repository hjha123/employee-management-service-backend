package com.java.backend.ems.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long departmentId;
}
