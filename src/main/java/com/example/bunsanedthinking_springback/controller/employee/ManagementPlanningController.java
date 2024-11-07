package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.mo.AddDepartmentDTO;
import com.example.bunsanedthinking_springback.dto.mo.AddOfficeSupplyDTO;
import com.example.bunsanedthinking_springback.dto.mo.UpdateDepartmentDTO;
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
	private ManagementPlanningSModel managementPlanningSModel;

	@PostMapping("/addDepartment")
	public void addDepartment(@RequestBody AddDepartmentDTO addDepartmentDTO) throws DuplicateDepartmentException {
		managementPlanningSModel.addDepartment(
				addDepartmentDTO.getHead_name(),
				addDepartmentDTO.getName(),
				addDepartmentDTO.getPurpose(),
				addDepartmentDTO.getTask());
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
	public void updateDepartment(@RequestBody UpdateDepartmentDTO updateDepartmentDTO) throws DuplicateDepartmentException, NotExistException{
		managementPlanningSModel.updateDepartment(
				updateDepartmentDTO.getId(),
				updateDepartmentDTO.getHead_name(),
				updateDepartmentDTO.getName(),
				updateDepartmentDTO.getPurpose(),
				updateDepartmentDTO.getTask());
	}
}
