package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.humanResource.EmployeeDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.humanResource.HumanResourceSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/humanResource")
public class HumanResourceController {
	@Autowired
	private HumanResourceSModel humanResourceSModel;

	@PostMapping("/addEmployee")
	public void addEmployee(@RequestBody EmployeeDTO employeeDTO) throws DuplicateResidentRegistrationNumberException {
		humanResourceSModel.addEmployee(employeeDTO);
	}

	@DeleteMapping("/deleteEmployee")
	public void deleteEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		humanResourceSModel.deleteEmployee(employeeId);
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException{
		return humanResourceSModel.getEmployee(employeeId);
	}

	public void requestAdditionalAllowance(){
		humanResourceSModel.requestAdditionalAllowance();
	}

	public void requestBenefit(){
		humanResourceSModel.requestBenefit();
	}

	@PatchMapping("/updateEmployee")
	public void updateEmployee(@RequestParam("index") int index, @RequestParam("input") String input,
			@RequestParam("employeeId") int employeeId) throws NotExistException {
		humanResourceSModel.updateEmployee(index, input, employeeId);
	}

	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee() {
		return humanResourceSModel.getAllEmployee();
	}

	@GetMapping("/getAllDepartment")
	public List<Department> getAllDepartment() {
		return humanResourceSModel.getAllDepartment();
	}

	@GetMapping("/getDepartment")
	public Department getDepartment(@RequestParam("departmentId") int departmentId) throws NotExistException {
		return humanResourceSModel.get(departmentId);
	}
}
