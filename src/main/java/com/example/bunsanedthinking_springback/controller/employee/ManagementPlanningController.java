package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.model.service.employee.managementPlanning.ManagementPlanningSModel;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/managementPlanning")
public class ManagementPlanningController {

	@Autowired
	private ManagementPlanningSModel managementPlanningSModel;

	@PostMapping("/addDepartment")
	public void addDepartment(@RequestParam String name, @RequestParam String task,
							  @RequestParam String purpose, @RequestParam String headName) throws DuplicateDepartmentException {
		managementPlanningSModel.addDepartment(name, task, purpose, headName);
	}

	@DeleteMapping("/deleteDepartment")
	public void deleteDepartment(@RequestParam int id) throws NotExistException {
		managementPlanningSModel.deleteDepartment(id);
	}

	@GetMapping("/getDepartment")
	public DepartmentVO getDepartment(@RequestParam int id) throws NotExistException{
		return managementPlanningSModel.getDepartment(id);
	}

	@PatchMapping("/updateDepartment")
	public void updateDepartment(@RequestParam int index, @RequestParam String input,
								 @RequestParam int id) throws DuplicateDepartmentException, NotExistException{
		managementPlanningSModel.updateDepartment(index, input, id);
	}
}
