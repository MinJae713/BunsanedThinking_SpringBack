package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentVO {
    private int id;
    private String head_name;
    private String name;
    private String purpose;
    private String task;

    public Department getEntity(
            List<OfficeSupply> officeSupplyList,
            ArrayList<Employee> employeeList){
        Department department = new Department();
        department.setId(id);
        department.setHeadName(head_name);
        department.setName(name);
        department.setPurpose(purpose);
        department.setTask(task);
        department.setOfficeSupplyList(officeSupplyList);
        department.setEmployeeList(employeeList);
        return department;
    }
}
