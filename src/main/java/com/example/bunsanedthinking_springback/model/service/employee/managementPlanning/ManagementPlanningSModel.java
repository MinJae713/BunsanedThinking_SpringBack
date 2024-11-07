package com.example.bunsanedthinking_springback.model.service.employee.managementPlanning;

import com.example.bunsanedthinking_springback.dto.mo.UpdateDepartmentDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.global.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.domain.department.DepartmentDModel;
import com.example.bunsanedthinking_springback.repository.DepartmentMapper;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagementPlanningSModel {
	@Autowired
	private DepartmentDModel departmentDModel;
	@Autowired
	public DepartmentMapper departmentMapper;

	public void addDepartment(String headName, String name, String purpose, String task) throws DuplicateDepartmentException{
		if (departmentMapper.findByName_ManagementPlanning(name) != null) {
			throw new DuplicateDepartmentException();
		}
		Integer maxId = departmentMapper.getMaxId_ManagementPlanning();
		int id;
		if (maxId == null) {
			id = Integer.parseInt(Department.DepartmentSerialNum + "1");
		} else {
			String index = (maxId + "").substring((Department.DepartmentSerialNum + "").length());
			id = Integer.parseInt((Department.DepartmentSerialNum + "") + (Integer.parseInt(index) + 1));
		}
		DepartmentVO departmentVO = new DepartmentVO();
		departmentVO.setId(id); // 새로운 ID 설정
		departmentVO.setName(name);
		departmentVO.setTask(task);
		departmentVO.setPurpose(purpose);
		departmentVO.setHead_name(headName);
		departmentMapper.insert_ManagementPlanning(departmentVO);
	}

	public void deleteDepartment(int id) throws NotExistException {
		if (departmentMapper.findById_ManagementPlanning(id) == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		departmentMapper.delete_ManagementPlanning(id);
	}

	public DepartmentVO getDepartment(int id) throws NotExistException {
		DepartmentVO departmentVO = departmentMapper.findById_ManagementPlanning(id);
		if (departmentVO == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		return departmentVO;
	}

	public void updateDepartment(UpdateDepartmentDTO updateDepartmentDTO)
			throws DuplicateDepartmentException {
		int id = updateDepartmentDTO.getId();
		int index = updateDepartmentDTO.getIndex();
		String input = updateDepartmentDTO.getInput();
		Department department = departmentDModel.getById(id);
		DepartmentVO departmentVO = new DepartmentVO();
		departmentVO.setId(department.getId());
		departmentVO.setName(department.getName());
		departmentVO.setPurpose(department.getPurpose());
		departmentVO.setHead_name(department.getHeadName());
		departmentVO.setTask(department.getTask());

		switch (index) {
			case 1:
				for (Department e : departmentDModel.getAll()) {
					if (e.getName().equals(input)) {
						throw new DuplicateDepartmentException();
					}
				}
				departmentVO.setName(input);
				departmentDModel.update(departmentVO);
				break;
			case 2:
				department.setTask(input);
				departmentDModel.update(departmentVO);
				break;
			case 3:
				department.setPurpose(input);
				departmentDModel.update(departmentVO);
				break;
			case 4:
				department.setHeadName(input);
				departmentDModel.update(departmentVO);
				break;
			default:
				break;
		}
	}
}
