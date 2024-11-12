package com.example.bunsanedthinking_springback.model.service.employee.managementPlanning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.AddDepartmentDTO;
import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.UpdateDepartmentDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.department.DepartmentEntityModel;

@Service
public class ManagementPlanningService {
	@Autowired
	private DepartmentEntityModel departmentEntityModel;
	public void addDepartment(AddDepartmentDTO addDepartmentDTO) throws DuplicateDepartmentException{
		boolean isExistDepartmentName = departmentEntityModel.getAll().stream()
				.anyMatch(department ->
						department.getName().equals(addDepartmentDTO.getName()));
		if(isExistDepartmentName)
			throw new DuplicateDepartmentException();

		Integer maxId = departmentEntityModel.getMaxId();
		int id;
		if (maxId == null) {
			id = Integer.parseInt(Department.DepartmentSerialNum + "1");
		} else {
			String index = (maxId + "").substring((Department.DepartmentSerialNum + "").length());
			id = Integer.parseInt((Department.DepartmentSerialNum + "") + (Integer.parseInt(index) + 1));
		}
		Department department = new Department(
				addDepartmentDTO.getName(),
				addDepartmentDTO.getTask(),
				addDepartmentDTO.getPurpose(),
				addDepartmentDTO.getHead_name()
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

	public void updateDepartment(UpdateDepartmentDTO updateDepartmentDTO)
			throws DuplicateDepartmentException, NotExistException {
		int id = updateDepartmentDTO.getId();
		int index = updateDepartmentDTO.getIndex();
		String input = updateDepartmentDTO.getInput();
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
}
