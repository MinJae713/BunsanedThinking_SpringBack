package com.example.bunsanedthinking_springback.model.service.employee.managementPlanning;

import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request.AddDepartmentRequest;
import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request.UpdateDepartmentRequest;
import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.response.DepartmentResponse;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.global.constants.serial.Serial;
import com.example.bunsanedthinking_springback.global.constants.service.employee.managementPlanning.ManagementPlanningConstants;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.department.DepartmentEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementPlanningService {

	@Autowired
	private Serial serial;

	@Autowired
	private DepartmentEntityModel departmentEntityModel;

	public void addDepartment(AddDepartmentRequest addDepartmentRequest) throws DuplicateDepartmentException{
		boolean isExistDepartmentName = departmentEntityModel.getAll().stream()
				.anyMatch(department -> department.getName().equals(addDepartmentRequest.getName()));
		if(isExistDepartmentName) {
			throw new DuplicateDepartmentException();
		}

		Integer maxId = departmentEntityModel.getMaxId();
		int id = NextIdGetter.getNextId(maxId, serial.getDepartment());

		Department department = new Department(
				addDepartmentRequest.getName(),
				addDepartmentRequest.getTask(),
				addDepartmentRequest.getPurpose(),
				addDepartmentRequest.getHead_name()
		);
		department.setId(id);
		departmentEntityModel.add(department);
	}

	public void deleteDepartment(int id) throws NotExistException {
		Department department = departmentEntityModel.getById(id);
		if (department == null) {
			throw new NotExistException(ManagementPlanningConstants.DEPARTMENT_INFORMATION_NOT_FOUND);
		}
		departmentEntityModel.delete(id);
	}

	public Department getDepartment(int id) throws NotExistException {
		Department department = departmentEntityModel.getById(id);
		if (department == null) {
			throw new NotExistException(ManagementPlanningConstants.DEPARTMENT_INFORMATION_NOT_FOUND);
		}
		return department;
	}

	public void updateDepartment(UpdateDepartmentRequest updateDepartmentRequest)
			throws DuplicateDepartmentException, NotExistException, RuntimeException {
		int id = updateDepartmentRequest.getId();
		int index = updateDepartmentRequest.getIndex();
		String input = updateDepartmentRequest.getInput();
		Department department = departmentEntityModel.getById(id);
		if (department == null){
			throw new NotExistException(ManagementPlanningConstants.DEPARTMENT_INFORMATION_NOT_FOUND);
		}
		switch (index) {
			case 1:
				for (Department e : departmentEntityModel.getAll()) {
					if (e.getName().equals(input)) {
						throw new DuplicateDepartmentException();
					}
				}
				if (input.length() > 10){
					throw new RuntimeException(ManagementPlanningConstants.NAME_LENGTH_MESSAGE);
				}
				if (!input.matches(ManagementPlanningConstants.NAME_FORMAT_REGEXP))
					throw new RuntimeException(ManagementPlanningConstants.NAME_FORMAT_MESSAGE);
				department.setName(input);
				departmentEntityModel.update(department);
				break;
			case 2:
				if (input.length() > 100){
					throw new RuntimeException(ManagementPlanningConstants.TASK_LENGTH_MESSAGE);
				}
				if (!input.matches(ManagementPlanningConstants.TASK_FORMAT_REGEXP))
					throw new RuntimeException(ManagementPlanningConstants.TASK_FORMAT_MESSAGE);
				department.setTask(input);
				departmentEntityModel.update(department);
				break;
			case 3:
				if (input.length() > 25){
					throw new RuntimeException(ManagementPlanningConstants.PURPOSE_LENGTH_MESSAGE);
				}
				if (!input.matches(ManagementPlanningConstants.PURPOSE_FORMAT_REGEXP))
					throw new RuntimeException(ManagementPlanningConstants.PURPOSE_FORMAT_MESSAGE);
				department.setPurpose(input);
				departmentEntityModel.update(department);
				break;
			case 4:
				if (input.length() > 20){
					throw new RuntimeException(ManagementPlanningConstants.HEAD_NAME_LENGTH_MESSAGE);
				}
				if (!input.matches(ManagementPlanningConstants.HEAD_NAME_FORMAT_REGEXP))
					throw new RuntimeException(ManagementPlanningConstants.HEAD_NAME_FORMAT_MESSAGE);
				department.setHeadName(input);
				departmentEntityModel.update(department);
				break;
		}
	}

	public List<DepartmentResponse> getAllDepartment(){
		return departmentEntityModel.getAll().stream()
				.map(DepartmentResponse::from)
				.toList();
	}
}
