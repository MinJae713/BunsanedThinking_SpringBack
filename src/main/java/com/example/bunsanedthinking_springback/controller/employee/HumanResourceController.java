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

import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.AddEmployeeRequest;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.UpdateEmployeeRequest;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.response.DepartmentResponse;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.response.ManagementEmployeeResponse;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.humanResource.HumanResourceService;

@RestController
@RequestMapping("/employee/humanResource")
public class HumanResourceController {
	@Autowired
	private HumanResourceService humanResourceService;

	@PostMapping("/addEmployee")
	public void addEmployee(@RequestBody AddEmployeeRequest addEmployeeRequest) throws
		DuplicateResidentRegistrationNumberException,
		ParseException {
		humanResourceService.addEmployee(addEmployeeRequest);
	}

	@DeleteMapping("/deleteEmployee")
	public void deleteEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		humanResourceService.deleteEmployee(employeeId);
	}

	@GetMapping("/getEmployee")
	public ManagementEmployeeResponse getEmployee(@RequestParam("employeeId") int employeeId) throws NotExistException {
		return humanResourceService.getEmployee(employeeId);
	}

	@GetMapping("/getEmployeeDetail")
	public ManagementEmployeeResponse getEmployeeDetail(@RequestParam("employeeId") int employeeId) throws
		NotExistException {
		return humanResourceService.getEmployeeDetail(employeeId);
	}

	public void requestAdditionalAllowance() {
		humanResourceService.requestAdditionalAllowance();
	}

	public void requestBenefit() {
		humanResourceService.requestBenefit();
	}

	@PatchMapping("/updateEmployee")
	public void updateEmployee(@RequestBody UpdateEmployeeRequest updateEmployeeRequest) throws
		NotExistException,
		ParseException {
		humanResourceService.updateEmployee(updateEmployeeRequest);
	}

	@GetMapping("/getAllEmployee")
	public List<ManagementEmployeeResponse> getAllEmployee() {
		return humanResourceService.getAllEmployee();
	}

	@GetMapping("/getAllDepartment")
	public List<DepartmentResponse> getAllDepartment() {
		return humanResourceService.getAllDepartment();
	}

	@GetMapping("/getDepartment")
	public DepartmentResponse getDepartment(@RequestParam("departmentId") int departmentId) throws
		NotExistException {
		return humanResourceService.get(departmentId);
	}
}
