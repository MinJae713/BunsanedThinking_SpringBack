package com.example.bunsanedthinking_springback.model.domain.department;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.model.domain.employee.EmployeeDModel;
import com.example.bunsanedthinking_springback.model.domain.officeSupply.OfficeSupplyDModel;
import com.example.bunsanedthinking_springback.repository.DepartmentMapper;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentDModel {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private OfficeSupplyDModel officeSupplyDModel;
    @Autowired
    private EmployeeDModel employeeDModel;
    public Department getById(int id) {
        DepartmentVO departmentVO = departmentMapper.findById_HumanResource(id).orElse(null);
        if (departmentVO != null) return null;
        List<OfficeSupply> officeSupplies = new ArrayList<OfficeSupply>();
        ArrayList<Employee> employees = new ArrayList<Employee>();
        officeSupplyDModel.getAll().stream().filter(e -> e.getDepartmentId() == id).forEach(e -> officeSupplies.add(e));
        employeeDModel.getAll().stream().filter(e -> e.getDepartmentID() == id).forEach(e -> employees.add(e));
        return departmentVO.getEntity(officeSupplies, employees);
    }
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<Department>();
        departmentMapper.getAll_HumanResource().stream().forEach(e -> departments.add(getById(e.getId())));
        return departments;
    }
    public int getMaxId() {
        return departmentMapper.getMaxId_ManagementPlanning();
    }
    public void add(DepartmentVO departmentVO) {
        departmentMapper.insert(departmentVO);
    }
    public void update(DepartmentVO departmentVO) {
        departmentMapper.update_ManagementPlanning(departmentVO);
    }
    public void delete(int id) {
        departmentMapper.delete_ManagementPlanning(id);
    }
}
