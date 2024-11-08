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
		if (departmentVO == null)
			return null;
		List<OfficeSupply> officeSupplies = new ArrayList<OfficeSupply>();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		officeSupplyDModel.getAll().stream().filter(e -> e.getDepartmentId() == id).forEach(officeSupplies::add);
		employeeDModel.getAll().stream().filter(e -> e.getDepartmentID() == id).forEach(employees::add);
		return departmentVO.getEntity(officeSupplies, employees);
	}

	public List<Department> getAll() {
		List<Department> departments = new ArrayList<Department>();
		departmentMapper.getAll_HumanResource().forEach(e -> departments.add(getById(e.getId())));
		return departments;
	}

	public Integer getMaxId() {
		return departmentMapper.getMaxId_ManagementPlanning();
	}

	public void add(Department department) {
		if (department == null) return;
		if (departmentMapper.findById_HumanResource(department.getId()).isPresent()) return;
		departmentMapper.insert(department.findVO());

		List<OfficeSupply> officeSupplies = department.getOfficeSupplyList();
		if (officeSupplies != null) officeSupplies.forEach(e -> officeSupplyDModel.add(e));

		List<Employee> employees = department.getEmployeeList();
		if (employees != null) employees.forEach(e -> employeeDModel.add(e));
	}

	public void update(Department department) {
		if (department == null) return;
		if (departmentMapper.findById_HumanResource(department.getId()).isEmpty()) return;

		List<OfficeSupply> officeSupplies = department.getOfficeSupplyList();
		if (officeSupplies != null) officeSupplies.forEach(e -> officeSupplyDModel.update(e));

		List<Employee> employees = department.getEmployeeList();
		if (employees != null) employees.forEach(e -> employeeDModel.update(e));

		departmentMapper.update_ManagementPlanning(department.findVO());
	}

	public void delete(int id) {
		if (departmentMapper.findById_HumanResource(id).isEmpty()) return;
		Department department = getById(id);

		List<OfficeSupply> officeSupplies = department.getOfficeSupplyList();
		if (officeSupplies != null) officeSupplies.forEach(e -> officeSupplyDModel.delete(e.getId()));

		List<Employee> employees = department.getEmployeeList();
		if (employees != null) employees.forEach(e -> employeeDModel.delete(e.getId()));

		departmentMapper.delete_ManagementPlanning(id);
	}
}
