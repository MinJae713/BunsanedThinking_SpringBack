package com.example.bunsanedthinking_springback.model.service.employee.managementPlanning;

import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.response.DepartmentResponse;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request.AddDepartmentRequest;
import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.request.UpdateDepartmentRequest;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.department.DepartmentEntityModel;

import java.util.List;

@Service
public class ManagementPlanningService {
	@Autowired
	private DepartmentEntityModel departmentEntityModel;

	@Value("${serials.department}")
	public int DEPARTMENT_SERIAL_NUMBER;

	public void addDepartment(AddDepartmentRequest addDepartmentRequest) throws DuplicateDepartmentException{
		boolean isExistDepartmentName = departmentEntityModel.getAll().stream()
				.anyMatch(department -> department.getName().equals(addDepartmentRequest.getName()));
		if(isExistDepartmentName) {
			throw new DuplicateDepartmentException();
		}

		Integer maxId = departmentEntityModel.getMaxId();
		int id = NextIdGetter.getNextId(maxId, DEPARTMENT_SERIAL_NUMBER);

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
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		departmentEntityModel.delete(id);
	}

	public Department getDepartment(int id) throws NotExistException {
		Department department = departmentEntityModel.getById(id);
		if (department == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		return department;
	}

	public void updateDepartment(UpdateDepartmentRequest updateDepartmentRequest)
			throws DuplicateDepartmentException, NotExistException {
		int id = updateDepartmentRequest.getId();
		int index = updateDepartmentRequest.getIndex();
		String input = updateDepartmentRequest.getInput();
		Department department = departmentEntityModel.getById(id);
		if (department == null){
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1:
				for (Department e : departmentEntityModel.getAll()) {
					if (e.getName().equals(input)) {
						throw new DuplicateDepartmentException();
					}
				}
				department.setName(input);
				departmentEntityModel.update(department);
				break;
			case 2:
				department.setTask(input);
				departmentEntityModel.update(department);
				break;
			case 3:
				department.setPurpose(input);
				departmentEntityModel.update(department);
				break;
			case 4:
				department.setHeadName(input);
				departmentEntityModel.update(department);
				break;
			default:
				break;
		}
	}

	public List<DepartmentResponse> getAllDepartment(){
		return departmentEntityModel.getAll().stream()
				.map(DepartmentResponse::from)
				.toList();
	}
}
