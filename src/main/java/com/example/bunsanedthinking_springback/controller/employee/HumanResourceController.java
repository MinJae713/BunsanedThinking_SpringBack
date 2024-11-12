package com.example.bunsanedthinking_springback.controller.employee;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.employee.humanResource.EmployeeDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.humanResource.HumanResourceService;

@RestController
@RequestMapping("/employee/humanResource")
public class HumanResourceController {
	@Autowired
	private HumanResourceService humanResourceService;

	@PostMapping("/addEmployee")
	public void addEmployee(@RequestBody EmployeeDTO employeeDTO) throws
		DuplicateResidentRegistrationNumberException,
		ParseException {
		humanResourceService.addEmployee(employeeDTO);
	}

	@DeleteMapping("/deleteEmployee")
	public void deleteEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		humanResourceService.deleteEmployee(employeeId);
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		return humanResourceService.getEmployee(employeeId);
	}

	public void requestAdditionalAllowance() {
		humanResourceService.requestAdditionalAllowance();
	}

	public void requestBenefit() {
		humanResourceService.requestBenefit();
	}

	@PatchMapping("/updateEmployee")
	public void updateEmployee(@RequestParam("index") int index, @RequestParam("input") String input,
		@RequestParam("employeeId") int employeeId) throws NotExistException {
		humanResourceService.updateEmployee(index, input, employeeId);
	}

	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee() {
		return humanResourceService.getAllEmployee();
	}

	@GetMapping("/getAllDepartment")
	public List<Department> getAllDepartment() {
		return humanResourceService.getAllDepartment();
	}

	@GetMapping("/getDepartment")
	public Department getDepartment(@RequestParam("departmentId") int departmentId) throws NotExistException {
		return humanResourceService.get(departmentId);
	}
}
