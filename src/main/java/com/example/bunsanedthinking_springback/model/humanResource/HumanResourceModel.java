package com.example.bunsanedthinking_springback.model.humanResource;

import com.example.bunsanedthinking_springback.dto.chan.EmployeeDTO;
import com.example.bunsanedthinking_springback.dto.chan.FamilyDTO;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.RelationshipType;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.DepartmentMapper;
import com.example.bunsanedthinking_springback.repository.EmployeeMapper;
import com.example.bunsanedthinking_springback.repository.FamilyMapper;
import com.example.bunsanedthinking_springback.repository.PaymentDetailMapper;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import com.example.bunsanedthinking_springback.vo.FamilyVO;
import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HumanResourceModel {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private FamilyMapper familyMapper;
	@Autowired
	private PaymentDetailMapper paymentDetailMapper;
	@Autowired
	private DepartmentMapper departmentMapper;

	public void addEmployee(EmployeeDTO employeeDTO) throws DuplicateResidentRegistrationNumberException {
		if (employeeMapper.isExistResidentRegistrationNumber(employeeDTO.getResidentRegistrationNumber()) == 1) {
			throw new DuplicateResidentRegistrationNumberException();
		}
		Integer maxId = employeeMapper.getMaxId_HumanResource();
		int index;
		if (maxId == null){
			index = 1;
		} else {
			int employeeSerialLength = ("" + Employee.EMPLOYEE_SERIAL_NUMBER).length();
			int teamIdLength = 3;
			index = Integer.parseInt((maxId+"").substring(employeeSerialLength + teamIdLength + 1));
			index++;
		}
		String compound = "" + Employee.EMPLOYEE_SERIAL_NUMBER + employeeDTO.getTeamId() + index;
		int id = Integer.parseInt(compound);
		LocalDate employmentDate = LocalDate.parse(employeeDTO.getEmploymentDate());
		EmployeePosition employeePosition = EmployeePosition.indexOf(employeeDTO.getEmployeePosition());
		EmployeeVO employeeVO = new EmployeeVO(id, employeeDTO.getAddress(), employeeDTO.getBankName(),
			employeeDTO.getBankAccount(), employmentDate, employeeDTO.getName(), employeeDTO.getPhoneNumber(),
			employeePosition.ordinal(), employeeDTO.getResidentRegistrationNumber(), employeeDTO.getSalary(),
			employeeDTO.getDepartmentID());
		employeeMapper.insert_HumanResource(employeeVO);
		addFamily(employeeVO, employeeDTO.getTempFamilyList());
	}

	private void addFamily(EmployeeVO employeeVO, List<FamilyDTO> tempFamilyList) {
		Integer maxId = familyMapper.getMaxId_HumanResource();
		int index;
		if (maxId == null) {
			index = 1;
		} else {
			int familySerialLength = ("" + Family.FAMILY_SERIAL_NUMBER).length();
			index = Integer.parseInt((maxId+"").substring(familySerialLength + 1));
			index++;
		}
		String compound = "" + Family.FAMILY_SERIAL_NUMBER + index;
		int id = Integer.parseInt(compound);
		for (FamilyDTO family : tempFamilyList) {
			RelationshipType relationshipType = RelationshipType.indexOf(family.getRelationship());
			familyMapper.insert_HumanResource(new FamilyVO(id, family.getBirthDate(), family.getName(),
				relationshipType.ordinal(), family.isSurvival(), employeeVO.getId()));
			id++;
		}
	}

	public void deleteEmployee(int id) throws NotExistException{
		familyMapper.deleteByEmployeeId_HumanResource(id);
		employeeMapper.delete_HumanResource(id);
	}

	public Employee getEmployee(int id) throws NotExistException{
		EmployeeVO employeeVO = employeeMapper.findById_HumanResource(id)
			.orElseThrow(() -> new NotExistException("해당하는 직원 정보가 존재하지 않습니다."));
		ArrayList<PaymentDetail> paymentDetailList = new ArrayList<>();
		for (PaymentDetailVO paymentDetailVO : paymentDetailMapper.findByEmployeeId_HumanResource(id)) {
			paymentDetailList.add(
				new PaymentDetail(paymentDetailVO.getAccount_holder(), paymentDetailVO.getBank(), paymentDetailVO.getBank_account(),
					paymentDetailVO.getMoney(), PaymentType.indexOf(paymentDetailVO.getPayment_type()),
					paymentDetailVO.getContract_id(), paymentDetailVO.getEmployee_id())
			);
		}
		ArrayList<Family> familyList = new ArrayList<>();
		for (FamilyVO familyVO : familyMapper.findByEmployeeId_HumanResource(id)) {
			familyList.add(
				new Family(java.sql.Date.valueOf(familyVO.getBirth_date()), familyVO.getEmployee_id(),
					familyVO.getId(), familyVO.getName(), RelationshipType.indexOf(familyVO.getRelationship()),
					familyVO.isSurvival())
			);
		}
		return new Employee(employeeVO.getAddress(), employeeVO.getBank_account(), employeeVO.getDepartment_id(),
			java.sql.Date.valueOf(employeeVO.getEmployment_date()), employeeVO.getBank_name(), familyList,
			employeeVO.getId(), employeeVO.getName(), paymentDetailList, employeeVO.getPhone_number(),
			EmployeePosition.indexOf(employeeVO.getPosition()), employeeVO.getResident_registration_number(),
			employeeVO.getSalary());
	}

	public void requestAdditionalAllowance(){
		System.out.println("Additional Allowance");
	}

	public void requestBenefit(){
		System.out.println("Request Benefit");
	}

	public void updateEmployee(int index, String input, int employeeId) throws NotExistException {
		EmployeeVO employeeVO = employeeMapper.findById_HumanResource(employeeId)
			.orElseThrow(() -> new NotExistException("해당하는 직원 정보가 존재하지 않습니다."));
		switch (index) {
		case 1 -> employeeVO.setName(input);
		case 2 -> employeeVO.setPosition((Integer.parseInt(input)-1));
		case 3 -> employeeVO.setAddress(input);
		case 4 -> employeeVO.setPhone_number(input);
		case 5 -> employeeVO.setBank_name(input);
		case 6 -> employeeVO.setBank_account(input);
		case 8 -> employeeVO.setDepartment_id(Integer.parseInt(input));
		case 9 -> employeeVO.setSalary(Integer.parseInt(input));
		default -> {
			return;
		}
		}
		employeeMapper.update_HumanResource(employeeVO);
	}

	public List<Employee> getAllEmployee() {
		List<Employee> result = new ArrayList<>();
		for (EmployeeVO employeeVO: employeeMapper.getAll_HumanResource()) {
			Employee employee = new Employee();
			employee.setId(employeeVO.getId());
			employee.setPosition(EmployeePosition.indexOf(employeeVO.getPosition()));
			employee.setDepartmentID(employeeVO.getDepartment_id());
			employee.setSalary(employeeVO.getSalary());
			result.add(employee);
		}
		return result;
	}

	public List<Department> getAllDepartment() {
		List<Department> result = new ArrayList<>();
		for (DepartmentVO departmentVO : departmentMapper.getAll_HumanResource()) {
			Department department = new Department();
			department.setId(departmentVO.getId());
			department.setName(departmentVO.getName());
			result.add(department);
		}
		return result;
	}

	public Department get(int departmentID) throws NotExistException {
		DepartmentVO departmentVO = departmentMapper.findById_HumanResource(departmentID)
			.orElseThrow(() -> new NotExistException("해당하는 부서 정보가 존재하지 않습니다."));
		Department department = new Department();
		department.setId(departmentVO.getId());
		department.setName(departmentVO.getName());
		department.setHeadName(departmentVO.getHead_name());
		department.setPurpose(departmentVO.getPurpose());
		department.setTask(departmentVO.getTask());
		return department;
	}
}
