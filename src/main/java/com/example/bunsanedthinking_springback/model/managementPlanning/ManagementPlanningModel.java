package com.example.bunsanedthinking_springback.model.managementPlanning;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementPlanningModel {
	@Autowired
	public DepartmentMapper departmentMapper;

	public void addDepartment(String name, String task, String purpose, String headName) throws DuplicateDepartmentException{
		if (departmentMapper.findByName_ManagementPlanning(name) != null) {
			throw new DuplicateDepartmentException();
		}
		Department department = new Department(name, task, purpose, headName);
		departmentMapper.insert_ManagementPlanning(department);
	}

	public void deleteDepartment(int id) throws NotExistException{
		if (departmentMapper.findById_ManagementPlanning(id) == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		departmentMapper.delete_ManagementPlanning(id);
	}

	public Department getDepartment(int id) throws NotExistException{
		Department department = departmentMapper.findById_ManagementPlanning(id);
		if (department == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		return department;
	}

	public void updateDepartment(int index, String input, int id) throws NotExistException {
		Department department = getDepartment(id);
		switch (index) {
			case 1:
				department.setName(input);
				break;
			case 2:
				department.setTask(input);
				break;
			case 3:
				department.setPurpose(input);
				break;
			case 4:
				department.setHeadName(input);
				break;
			default:
				throw new IllegalArgumentException("유효하지 않은 선택입니다. 올바른 값을 입력하세요.");
		}
		departmentMapper.update_ManagementPlanning(department);
	}
}