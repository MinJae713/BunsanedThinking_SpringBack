package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.department.DepartmentList;
import com.example.bunsanedthinking_springback.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.managementPlanning.ManagementPlanningModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee/managementPlanning")
public class ManagementPlanningController {
	@Autowired
	private ManagementPlanningModel managementPlanningModel;

	public void addDepartment(String name, String task, String purpose, String headName, DepartmentList departmentList) throws DuplicateDepartmentException {
		managementPlanningModel.addDepartment(name, task, purpose, headName, departmentList);
	}

	public void deleteDepartment(DepartmentList departmentList, int id) throws NotExistException {
		managementPlanningModel.deleteDepartment(departmentList, id);
	}

	public Department getDepartment(DepartmentList departmentList, int id) throws NotExistException{
		return managementPlanningModel.getDepartment(departmentList, id);
	}

	public void updateDepartment(int index, String input, Department department, DepartmentList departmentList) throws DuplicateDepartmentException, NotExistException{
		managementPlanningModel.updateDepartment(index, input, department, departmentList);
	}
}
