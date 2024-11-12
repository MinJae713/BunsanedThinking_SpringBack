package com.example.bunsanedthinking_springback.model.service.employee.humanResource;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.humanResource.EmployeeDTO;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.FamilyDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.RelationshipType;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.department.DepartmentDModel;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeDModel;
import com.example.bunsanedthinking_springback.model.entityModel.family.FamilyEntityModel;

@Service
public class HumanResourceService {
	@Autowired
	private EmployeeDModel employeeDModel;
	@Autowired
	private FamilyEntityModel familyDModel;
	@Autowired
	private DepartmentDModel departmentDModel;

	public void addEmployee(EmployeeDTO employeeDTO) throws DuplicateResidentRegistrationNumberException,
		ParseException {
		checkResidentRegistrationNumber(employeeDTO.getResidentRegistrationNumber());
		Integer employeeMaxId = employeeDModel.getMaxId();
		int employeeId;
		if (employeeMaxId == null) {
			employeeId = Integer.parseInt(("" + Employee.EMPLOYEE_SERIAL_NUMBER) + employeeDTO.getTeamId() + "1");
		} else {
			int employeeSerialLength = ("" + Employee.EMPLOYEE_SERIAL_NUMBER).length();
			int teamIdLength = 3;
			int index = Integer.parseInt((employeeMaxId + "").substring(employeeSerialLength + teamIdLength)) + 1;
			employeeId = Integer.parseInt((Employee.EMPLOYEE_SERIAL_NUMBER + "") + employeeDTO.getTeamId() + index);
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date employmentDate = formatter.parse(employeeDTO.getEmploymentDate());
		EmployeePosition employeePosition = EmployeePosition.indexOf(employeeDTO.getEmployeePosition());

		ArrayList<Family> familyList = createFamilyList(employeeId, employeeDTO.getTempFamilyList());

		Employee employee = new Employee(employeeDTO.getAddress(), employeeDTO.getBankAccount(),
			employeeDTO.getTeamId(),
			employmentDate, employeeDTO.getBankName(), familyList, employeeId, employeeDTO.getName(),
			null, employeeDTO.getPhoneNumber(), employeePosition,
			employeeDTO.getResidentRegistrationNumber(), employeeDTO.getSalary(), null);
		employeeDModel.add(employee);
	}

	private void checkResidentRegistrationNumber(String residentRegistrationNumber)
		throws DuplicateResidentRegistrationNumberException {
		// TODO isExist같은 SQL로 수정 해야됨
		for (Employee employee : employeeDModel.getAll()) {
			if (employee.getResidentRegistrationNumber().equals(residentRegistrationNumber)) {
				throw new DuplicateResidentRegistrationNumberException();
			}
		}
	}

	private ArrayList<Family> createFamilyList(int employeeId, List<FamilyDTO> tempFamilyList) {
		Integer maxId = familyDModel.getMaxId();
		int familyId;
		int index;
		if (maxId == null) {
			familyId = Integer.parseInt(("" + Family.FAMILY_SERIAL_NUMBER) + 1);
			index = 1;
		} else {
			int familySerialLength = ("" + Family.FAMILY_SERIAL_NUMBER).length();
			index = Integer.parseInt((maxId + "").substring(familySerialLength)) + 1;
			familyId = Integer.parseInt(("" + Family.FAMILY_SERIAL_NUMBER) + index);
		}
		ArrayList<Family> familyList = new ArrayList<>();
		for (FamilyDTO familyDTO : tempFamilyList) {
			RelationshipType relationshipType = RelationshipType.indexOf(familyDTO.getRelationship());
			Family family = new Family(Date.valueOf(familyDTO.getBirthDate()), employeeId, familyId,
				familyDTO.getName(), relationshipType, familyDTO.isSurvival());
			familyList.add(family);
			index++;
			familyId = Integer.parseInt(("" + Family.FAMILY_SERIAL_NUMBER) + index);
		}
		return familyList;
	}

	public void deleteEmployee(int id) throws NotExistException {
		if (employeeDModel.getById(id) == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		employeeDModel.delete(id);
	}

	public Employee getEmployee(int id) throws NotExistException {
		Employee employee = employeeDModel.getById(id);
		if (employee == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		return employee;
	}

	public void requestAdditionalAllowance() {
		System.out.println("Additional Allowance");
	}

	public void requestBenefit() {
		System.out.println("Request Benefit");
	}

	public void updateEmployee(int index, String input, int employeeId) throws NotExistException {
		Employee employee = employeeDModel.getById(employeeId);
		if (employee == null)
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		switch (index) {
			case 1 -> employee.setName(input);
			case 2 -> employee.setPosition(EmployeePosition.indexOf(Integer.parseInt(input)));
			case 3 -> employee.setAddress(input);
			case 4 -> employee.setPhoneNumber(input);
			case 5 -> employee.setBankName(input);
			case 6 -> employee.setBankAccount(input);
			case 8 -> employee.setDepartmentID(Integer.parseInt(input));
			case 9 -> employee.setSalary(Integer.parseInt(input));
			default -> {
				return;
			}
		}
		employeeDModel.update(employee);
	}

	public List<Employee> getAllEmployee() {
		return employeeDModel.getAll();
	}

	public List<Department> getAllDepartment() {
		return departmentDModel.getAll();
	}

	public Department get(int departmentID) throws NotExistException {
		Department department = departmentDModel.getById(departmentID);
		if (department == null)
			throw new NotExistException("해당하는 부서 정보가 존재하지 않습니다.");
		return department;
	}
}
