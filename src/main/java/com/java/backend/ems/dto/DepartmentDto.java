package com.java.backend.ems.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentDto {
    private long id;
    private String departmentName;
    private String departmentDescription;
}
