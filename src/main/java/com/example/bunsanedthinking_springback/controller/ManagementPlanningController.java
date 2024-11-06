package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.department.DepartmentList;
import com.example.bunsanedthinking_springback.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.managementPlanning.ManagementPlanningModel;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/managementPlanning")
public class ManagementPlanningController {

	@Autowired
	private ManagementPlanningModel managementPlanningModel;

	@PostMapping("/addDepartment")
	public void addDepartment(@RequestParam String name, @RequestParam String task,
							  @RequestParam String purpose, @RequestParam String headName) throws DuplicateDepartmentException {
		managementPlanningModel.addDepartment(name, task, purpose, headName);
	}

	@DeleteMapping("/deleteDepartment")
	public void deleteDepartment(@RequestParam int id) throws NotExistException {
		managementPlanningModel.deleteDepartment(id);
	}

	@GetMapping("/getDepartment")
	public DepartmentVO getDepartment(@RequestParam int id) throws NotExistException{
		return managementPlanningModel.getDepartment(id);
	}

	@PatchMapping("/updateDepartment")
	public void updateDepartment(@RequestParam int index, @RequestParam String input,
								 @RequestParam int id) throws DuplicateDepartmentException, NotExistException{
		managementPlanningModel.updateDepartment(index, input, id);
	}
}
