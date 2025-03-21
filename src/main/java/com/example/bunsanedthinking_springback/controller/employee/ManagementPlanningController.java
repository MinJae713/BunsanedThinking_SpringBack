package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request.AddDepartmentRequest;
import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request.UpdateDepartmentRequest;
import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.response.DepartmentResponse;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.managementPlanning.ManagementPlanningService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/employee/managementPlanning")
public class ManagementPlanningController {

	@Autowired
	private ManagementPlanningService managementPlanningSModel;

	@PostMapping("/addDepartment")
	public void addDepartment(@Valid @RequestBody AddDepartmentRequest addDepartmentRequest)
			throws DuplicateDepartmentException {
		managementPlanningSModel.addDepartment(addDepartmentRequest);
	}

	@DeleteMapping("/deleteDepartment")
	public void deleteDepartment(@RequestParam int id) throws NotExistException {
		managementPlanningSModel.deleteDepartment(id);
	}

	@GetMapping("/getDepartment")
	public Department getDepartment(@RequestParam int id) throws NotExistException{
		return managementPlanningSModel.getDepartment(id);
	}

	@PatchMapping("/updateDepartment")
	public void updateDepartment(@RequestBody UpdateDepartmentRequest updateDepartmentRequest)
			throws DuplicateDepartmentException, NotExistException, RuntimeException {
		managementPlanningSModel.updateDepartment(updateDepartmentRequest);
	}

	@GetMapping("/getAllDepartment")
	public List<DepartmentResponse> getAllDepartment() {
		return managementPlanningSModel.getAllDepartment();
	}

}
