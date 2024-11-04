package com.example.bunsanedthinking_springback.model.humanResource;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.department.DepartmentList;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeeList;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.FamilyList;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

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

	public void addEmployee(int teamId, String name, EmployeePosition employeePosition, String address, String phoneNumber, String bankName, String bankAccount, String residentRegistrationNumber, int departmentID, int salary, String dateOfemployment, EmployeeList employeeList, ArrayList<Family> tempFamilyList, FamilyList familyList) throws DuplicateResidentRegistrationNumberException {
		for (EmployeeVO employee : employeeMapper.getAll_HumanResource()) {
			if (employee.getResident_registration_number().equals(residentRegistrationNumber)) {
				throw new DuplicateResidentRegistrationNumberException();
			}
		}
		Date date = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			date = formatter.parse(dateOfemployment);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Employee employee = null;
		// int[] teamIds = {110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230};
		// for (int id : teamIds) {
		// 	if (teamId == id) {
		// 		employee = new Employee(name, employeePosition,address, phoneNumber,
		// 				bankName, bankAccount,residentRegistrationNumber, departmentID, salary,date);
		// 		employeeList.add(employee);
		// 	}
		// }
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
		String compound = "" + Employee.EMPLOYEE_SERIAL_NUMBER + teamId + index;
		int id = Integer.parseInt(compound);
		EmployeeVO employeeVO = new EmployeeVO(id, address, bankName, bankAccount, LocalDate.now(), name,
			phoneNumber, employeePosition.ordinal(), residentRegistrationNumber, salary, departmentID);
		employeeMapper.insert_HumanResource(employeeVO);
		addFamily(employeeVO, tempFamilyList);
	}

	private void addFamily(EmployeeVO employeeVO, ArrayList<Family> tempFamilyList) {
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
		for (Family family : tempFamilyList) {
			familyMapper.insert_HumanResource(new FamilyVO(id, LocalDate.parse(family.getBirthDate(), DateTimeFormatter.ISO_DATE), family.getName(), family.getRelationship().ordinal(), family.isSurvival(), employeeVO.getId()));
			id++;
		}
	}

	public void deleteEmployee(EmployeeList employeeList, int id) throws NotExistException{
		familyMapper.deleteByEmployeeId_HumanResource(id);
		employeeMapper.delete_HumanResource(id);
	}

	public Employee getEmployee(EmployeeList employeeList, int id) throws NotExistException{
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

	public void updateEmployee(int index, String input, Employee employee, EmployeeList employeeList) throws NotExistException {
		EmployeeVO employeeVO = employeeMapper.findById_HumanResource(employee.getId())
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
	public ArrayList<Employee> getAll(EmployeeList employeeList) {
		ArrayList<Employee> result = new ArrayList<>();
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

	public ArrayList<Department> getAll(DepartmentList departmentList) {
		ArrayList<Department> result = new ArrayList<>();
		for (DepartmentVO departmentVO : departmentMapper.getAll_HumanResource()) {
			Department department = new Department();
			department.setId(departmentVO.getId());
			department.setName(departmentVO.getName());
			result.add(department);
		}
		return result;
	}

	public Department get(DepartmentList departmentList, int departmentID) throws NotExistException {
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
