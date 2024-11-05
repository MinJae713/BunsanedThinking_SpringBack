package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.dto.chan.EmployeeDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.department.DepartmentList;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeeList;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.FamilyList;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.humanResource.HumanResourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee/humanResource")
public class HumanResourceController {
	@Autowired
	private HumanResourceModel humanResourceModel;

	@PostMapping("/addEmployee")
	public void addEmployee(@RequestBody EmployeeDTO employeeDTO) throws DuplicateResidentRegistrationNumberException {
		humanResourceModel.addEmployee(employeeDTO);
	}

	@DeleteMapping("/deleteEmployee")
	public void deleteEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		humanResourceModel.deleteEmployee(employeeId);
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException{
		return humanResourceModel.getEmployee(employeeId);
	}

	public void requestAdditionalAllowance(){
		humanResourceModel.requestAdditionalAllowance();
	}

	public void requestBenefit(){
		humanResourceModel.requestBenefit();
	}

	@PatchMapping("/updateEmployee")
	public void updateEmployee(@RequestParam("index") int index, @RequestParam("input") String input,
			@RequestParam("employeeId") int employeeId) throws NotExistException {
		humanResourceModel.updateEmployee(index, input, employeeId);
	}

	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee() {
		return humanResourceModel.getAllEmployee();
	}

	@GetMapping("/getAllDepartment")
	public List<Department> getAllDepartment() {
		return humanResourceModel.getAllDepartment();
	}

	@GetMapping("/getDepartment")
	public Department getDepartment(@RequestParam("departmentId") int departmentId) throws NotExistException {
		return humanResourceModel.get(departmentId);
	}
}
