package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.mo.AddDepartmentDTO;
import com.example.bunsanedthinking_springback.dto.mo.UpdateDepartmentDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.managementPlanning.ManagementPlanningSModel;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/managementPlanning")
public class ManagementPlanningController {

	@Autowired
	private ManagementPlanningSModel managementPlanningSModel;

//	@PostMapping("/addDepartment")
//	public void addDepartment(@RequestBody AddDepartmentDTO addDepartmentDTO) throws DuplicateDepartmentException {
//		managementPlanningSModel.addDepartment(
//				addDepartmentDTO.getHead_name(),
//				addDepartmentDTO.getName(),
//				addDepartmentDTO.getPurpose(),
//				addDepartmentDTO.getTask());
//	}

	@PostMapping("/addDepartment")
	public void addDepartment(@RequestBody AddDepartmentDTO addDepartmentDTO) throws DuplicateDepartmentException {
		managementPlanningSModel.addDepartment(addDepartmentDTO);
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
	public void updateDepartment(@RequestBody UpdateDepartmentDTO updateDepartmentDTO)
			throws DuplicateDepartmentException, NotExistException {
		managementPlanningSModel.updateDepartment(updateDepartmentDTO);
	}
}
