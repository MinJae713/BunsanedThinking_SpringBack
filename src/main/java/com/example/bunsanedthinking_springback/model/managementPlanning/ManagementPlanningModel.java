package com.example.bunsanedthinking_springback.model.managementPlanning;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.exception.DuplicateDepartmentException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.DepartmentMapper;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;
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

		Integer maxId = departmentMapper.getMaxId_ManagementPlanning();
		int id;
		if (maxId == null) {
			id = Integer.parseInt(OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "1");
		} else {
			String index = (maxId + "").substring((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "").length());
			id = Integer.parseInt((OfficeSupply.OFFICESUPPLY_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}
		DepartmentVO departmentVO = new DepartmentVO();
		departmentVO.setId(id); // 새로운 ID 설정
		departmentVO.setName(name);
		departmentVO.setTask(task);
		departmentVO.setPurpose(purpose);
		departmentVO.setHead_name(headName);
		departmentMapper.insert_ManagementPlanning(departmentVO);
	}

	public void deleteDepartment(int id) throws NotExistException{
		if (departmentMapper.findById_ManagementPlanning(id) == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		departmentMapper.delete_ManagementPlanning(id);
	}

	public DepartmentVO getDepartment(int id) throws NotExistException{
		DepartmentVO departmentVO = departmentMapper.findById_ManagementPlanning(id);
		if (departmentVO == null) {
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		}
		return departmentVO;
	}

	public void updateDepartment(int index, String input, int id) throws NotExistException {
		DepartmentVO departmentVO = getDepartment(id);
		switch (index) {
			case 1 -> departmentVO.setName(input);
			case 2 -> departmentVO.setTask(input);
			case 3 -> departmentVO.setPurpose(input);
			case 4 -> departmentVO.setHead_name(input);
			default -> throw new IllegalArgumentException("유효하지 않은 선택입니다. 올바른 값을 입력하세요.");
		}
		departmentMapper.update_ManagementPlanning(departmentVO);
	}
}