package com.example.bunsanedthinking_springback.entity.employee;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee implements Cloneable {

	private String address;
	private String bankAccount;
	private int departmentID;
	protected Date employmentDate;
	private String bankName;
	private List<Family> familyList;
	private int id;
	private String name;
	private List<PaymentDetail> paymentDetailList;
	private String phoneNumber;
	protected EmployeePosition position;
	private String residentRegistrationNumber;
	private int salary;
	private List<Contract> contractList;

	public Employee(String name, EmployeePosition employeePosition, String address,
		String phoneNumber, String bankName, String bankAccount, String residentRegistrationNumber,
		int departmentID, int salary, Date dateOfemployment) {
		this.setName(name);
		this.setPosition(employeePosition);
		this.setAddress(address);
		this.setPhoneNumber(phoneNumber);
		this.setBankName(bankName);
		this.setBankAccount(bankAccount);
		this.setResidentRegistrationNumber(residentRegistrationNumber);
		this.setDepartmentID(departmentID);
		this.setSalary(salary);
		this.setEmploymentDate(dateOfemployment);
	}

	public Employee(Employee employee) {
		address = employee.getAddress();
		bankAccount = employee.getBankAccount();
		departmentID = employee.getDepartmentID();
		employmentDate = employee.getEmploymentDate();
		bankName = employee.getBankName();
		id = employee.getId();
		name = employee.getName();
		phoneNumber = employee.getPhoneNumber();
		position = employee.getPosition();
		residentRegistrationNumber = employee.getResidentRegistrationNumber();
		salary = employee.getSalary();

		familyList = employee.getFamilyList();
		paymentDetailList = employee.getPaymentDetailList();
		contractList = employee.getContractList();
	}

	public EmployeeVO findEmployeeVO() {
		LocalDate lEmploymentDate = new java.util.Date(employmentDate.getTime()).toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		return new EmployeeVO(id, address, bankName,
			bankAccount, lEmploymentDate, name,
			phoneNumber, position.ordinal(),
			residentRegistrationNumber,
			salary, departmentID);
	}
}

