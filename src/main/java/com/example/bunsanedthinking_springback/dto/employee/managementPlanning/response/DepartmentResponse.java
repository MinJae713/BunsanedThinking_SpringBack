package com.example.bunsanedthinking_springback.dto.employee.managementPlanning.response;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DepartmentResponse {
    private int id;
    private String name;
    private List<Employee> employeeList;
    private String headName;

    public static DepartmentResponse from(Department department){
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getEmployeeList(),
                department.getHeadName()
        );
    }
}
