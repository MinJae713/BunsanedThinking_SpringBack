package com.example.bunsanedthinking_springback.model.entityModel.department;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.officeSupply.OfficeSupplyEntityModel;
import com.example.bunsanedthinking_springback.repository.DepartmentMapper;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentEntityModel {
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private OfficeSupplyEntityModel officeSupplyDModel;
	@Autowired
	private EmployeeEntityModel employeeEntityModel;
	@ReadOnly
	public Department getById(int id) {
		DepartmentVO departmentVO = departmentMapper.getById(id).orElse(null);
		if (departmentVO == null)
			return null;
		List<OfficeSupply> officeSupplies = new ArrayList<OfficeSupply>();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		officeSupplyDModel.getAll().stream().filter(e -> e.getDepartmentId() == id).forEach(officeSupplies::add);
		employeeEntityModel.getAll().stream().filter(e -> e.getDepartmentId() == id).forEach(employees::add);
		return departmentVO.getEntity(officeSupplies, employees);
	}
	@ReadOnly
	public List<Department> getAll() {
		List<Department> departments = new ArrayList<Department>();
		departmentMapper.getAll().forEach(e -> departments.add(getById(e.getId())));
		return departments;
	}
	@ReadOnly
	public Integer getMaxId() {
		return departmentMapper.getMaxId();
	}

	public void add(Department department) {
		if (department == null)
			return;
		if (departmentMapper.getById(department.getId()).isPresent())
			return;
		departmentMapper.insert(department.findVO());

		List<OfficeSupply> officeSupplies = department.getOfficeSupplyList();
		if (officeSupplies != null)
			officeSupplies.forEach(e -> officeSupplyDModel.add(e));

		List<Employee> employees = department.getEmployeeList();
		if (employees != null)
			employees.forEach(e -> employeeEntityModel.add(e));
	}

	public void update(Department department) {
		if (department == null)
			return;
		if (departmentMapper.getById(department.getId()).isEmpty())
			return;

		List<OfficeSupply> officeSupplies = department.getOfficeSupplyList();
		if (officeSupplies != null)
			officeSupplies.forEach(e -> officeSupplyDModel.update(e));

		List<Employee> employees = department.getEmployeeList();
		if (employees != null)
			employees.forEach(e -> employeeEntityModel.update(e));

		departmentMapper.update(department.findVO());
	}

	public void delete(int id) {
		if (departmentMapper.getById(id).isEmpty())
			return;
		Department department = getById(id);

		List<OfficeSupply> officeSupplies = department.getOfficeSupplyList();
		if (officeSupplies != null)
			officeSupplies.forEach(e -> officeSupplyDModel.delete(e.getId()));

		List<Employee> employees = department.getEmployeeList();
		if (employees != null)
			employees.forEach(e -> employeeEntityModel.delete(e.getId()));

		departmentMapper.deleteById(id);
	}
}
