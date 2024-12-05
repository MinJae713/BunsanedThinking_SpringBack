package com.example.bunsanedthinking_springback.model.service.employee.humanResource;

import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.AddEmployeeRequest;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.CreateFamilyListRequest;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.UpdateEmployeeRequest;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.request.UpdateFamilyRequest;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.response.DepartmentResponse;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.response.ManagementEmployeeDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.humanResource.response.ManagementEmployeeResponse;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.serial.Serial;
import com.example.bunsanedthinking_springback.global.constants.service.employee.humanResource.HumanResourceConstants;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.department.DepartmentEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.family.FamilyEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HumanResourceService {

	@Autowired
	private Serial serial;

	@Autowired
	private EmployeeEntityModel employeeEntityModel;
	@Autowired
	private FamilyEntityModel familyEntityModel;
	@Autowired
	private DepartmentEntityModel departmentEntityModel;

	public void addEmployee(AddEmployeeRequest addEmployeeRequest) throws DuplicateResidentRegistrationNumberException,
		ParseException {
		checkResidentRegistrationNumber(addEmployeeRequest.getResidentRegistrationNumber());
		Integer employeeMaxId = employeeEntityModel.getMaxId();
		int employeeId;
		if (employeeMaxId == null) {
			employeeId = Integer.parseInt(serial.getEmployee() + HumanResourceConstants.ONE);
		} else {
			employeeId = NextIdGetter.getNextId(employeeMaxId, serial.getEmployee());
		}

		SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
		java.util.Date employmentDate = formatter.parse(addEmployeeRequest.getEmploymentDate());

		ArrayList<Family> familyList = createFamilyList(employeeId, addEmployeeRequest.getTempFamilyList());

		Employee employee = new Employee(addEmployeeRequest.getAddress(), addEmployeeRequest.getBankAccount(),
			addEmployeeRequest.getDepartmentId(),
			employmentDate, addEmployeeRequest.getBankName(), familyList, employeeId, addEmployeeRequest.getName(),
			new ArrayList<>(), addEmployeeRequest.getPhoneNumber(), addEmployeeRequest.getEmployeePosition(),
			addEmployeeRequest.getResidentRegistrationNumber(), addEmployeeRequest.getSalary(), new ArrayList<>(), null,
			null, null);
		employeeEntityModel.add(employee);
	}

	private void checkResidentRegistrationNumber(String residentRegistrationNumber)
		throws DuplicateResidentRegistrationNumberException {
		for (Employee employee : employeeEntityModel.getAll()) {
			if (employee.getResidentRegistrationNumber().equals(residentRegistrationNumber)) {
				throw new DuplicateResidentRegistrationNumberException();
			}
		}
	}

	private ArrayList<Family> createFamilyList(int employeeId, List<CreateFamilyListRequest> tempFamilyList) {
		Integer familyMaxId = familyEntityModel.getMaxId();
		int familyId;
		if (familyMaxId == null) {
			familyId = Integer.parseInt((CommonConstants.STRING_EMPTY + serial.getFamily()) + 1);
		} else {
			familyId = NextIdGetter.getNextId(familyMaxId, serial.getFamily());
		}
		familyMaxId = familyId;
		ArrayList<Family> familyList = new ArrayList<>();
		for (CreateFamilyListRequest createFamilyListRequest : tempFamilyList) {
			Family family = new Family(Date.valueOf(createFamilyListRequest.getBirthDate()), employeeId, familyId,
				createFamilyListRequest.getName(), createFamilyListRequest.getRelationship(),
				createFamilyListRequest.isSurvival());
			familyList.add(family);
			familyId = NextIdGetter.getNextId(familyMaxId, serial.getFamily());
			familyMaxId = familyId;
		}
		return familyList;
	}

	public void deleteEmployee(int id) throws NotExistException {
		if (employeeEntityModel.getById(id) == null)
			throw new NotExistException(HumanResourceConstants.EMPLOYEE_INFORMATION_NOT_FOUND);
		employeeEntityModel.delete(id);
	}

	public ManagementEmployeeResponse getEmployee(int id) throws NotExistException {
		Employee employee = employeeEntityModel.getById(id);
		if (employee == null)
			throw new NotExistException(HumanResourceConstants.EMPLOYEE_INFORMATION_NOT_FOUND);
		return ManagementEmployeeResponse.from(employee);
	}

	public ManagementEmployeeDetailResponse getEmployeeDetail(int id) throws NotExistException {
		Employee employee = employeeEntityModel.getById(id);
		if (employee == null)
			throw new NotExistException(HumanResourceConstants.EMPLOYEE_INFORMATION_NOT_FOUND);
		return ManagementEmployeeResponse.fromWithDetail(employee);
	}

	public void requestAdditionalAllowance() {
		System.out.println(HumanResourceConstants.ADDITIONAL_ALLOWANCE);
	}

	public void requestBenefit() {
		System.out.println(HumanResourceConstants.REQUEST_BENEFIT);
	}

	public void updateEmployee(UpdateEmployeeRequest updateEmployeeRequest) throws NotExistException, ParseException {
		Employee employee = employeeEntityModel.getById(updateEmployeeRequest.getId());
		if (employee == null)
			throw new NotExistException(HumanResourceConstants.EMPLOYEE_INFORMATION_NOT_FOUND);
		List<UpdateFamilyRequest> updateFamilyRequestList = updateEmployeeRequest.getTempFamilyList();
		for (UpdateFamilyRequest updateFamilyRequest : updateFamilyRequestList) {
			System.out.println(updateFamilyRequest.getId());
		}
		List<CreateFamilyListRequest> newFamilyRequestList = updateFamilyRequestList.stream()
			.filter(updateFamilyRequest -> updateFamilyRequest.getId() == null)
			.map(UpdateFamilyRequest::toCreateRequest)
			.collect(Collectors.toList());

		List<Family> newFamilyList = createFamilyList(employee.getId(), newFamilyRequestList);
		newFamilyList.forEach(familyEntityModel::add);
		Employee updatedEmployee = updateEmployeeRequest.toEntity(employee);
		employeeEntityModel.update(updatedEmployee);
	}

	public List<ManagementEmployeeResponse> getAllEmployee() {
		return employeeEntityModel.getAll().stream()
			.map(ManagementEmployeeResponse::from)
			.toList();
	}

	public List<DepartmentResponse> getAllDepartment() {
		return departmentEntityModel.getAll().stream()
			.map(DepartmentResponse::from)
			.toList();
	}

	public DepartmentResponse get(int departmentID) throws NotExistException {
		Department department = departmentEntityModel.getById(departmentID);
		if (department == null)
			throw new NotExistException(HumanResourceConstants.DEPARTMENT_INFORMATION_NOT_FOUND);
		return DepartmentResponse.from(department);
	}
}
