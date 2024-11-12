package com.example.bunsanedthinking_springback.model.service.employee.managementPlanning;

import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.AddDepartmentDTO;
import com.example.bunsanedthinking_springback.dto.employee.managementPlanning.UpdateDepartmentDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.department.DepartmentDModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementPlanningService {
	@Autowired
	private DepartmentDModel departmentDModel;

	public void addDepartment(AddDepartmentDTO addDepartmentDTO) throws DuplicateDepartmentException{
		boolean isExistDepartmentName = departmentDModel.getAll().stream()
				.anyMatch(department ->
						department.getName().equals(addDepartmentDTO.getName()));
		if(isExistDepartmentName)
			throw new DuplicateDepartmentException();

		Integer maxId = departmentDModel.getMaxId();
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
		departmentDModel.add(department);
	}

	public void deleteDepartment(int id) throws NotExistException {
		Department department = departmentDModel.getById(id);
		if (department == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		departmentDModel.delete(id);
	}

	public Department getDepartment(int id) throws NotExistException {
		Department department = departmentDModel.getById(id);
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
		Department department = departmentDModel.getById(id);
		if (department == null){
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1:
				for (Department e : departmentDModel.getAll()) {
					if (e.getName().equals(input)) {
						throw new DuplicateDepartmentException();
					}
				}
				department.setName(input);
				departmentDModel.update(department);
				break;
			case 2:
				department.setTask(input);
				departmentDModel.update(department);
				break;
			case 3:
				department.setPurpose(input);
				departmentDModel.update(department);
				break;
			case 4:
				department.setHeadName(input);
				departmentDModel.update(department);
				break;
			default:
				break;
		}
	}
}
