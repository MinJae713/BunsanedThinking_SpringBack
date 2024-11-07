package com.example.bunsanedthinking_springback.entity.employee;

import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Cloneable {

	public static final int EMPLOYEE_SERIAL_NUMBER = 600;

	private String address;
	private String bankAccount;
	private int departmentID;
	protected Date employmentDate;
	private String bankName;
	private ArrayList<Family> familyList;
	private int id;
	private String name;
	private ArrayList<PaymentDetail> paymentDetailList;
	private String phoneNumber;
	protected EmployeePosition position;
	private String residentRegistrationNumber;
	private int salary;
//	private List<Contract> contractList;
	// 의미상 넣어놨수다 - 찬님 기존 코드랑 충돌나서 주석처리
	// (DB로 따지믄 직원이 계약을 갖고있다 이런 의미임다)

	public Employee(String name, EmployeePosition employeePosition, String address,
					String phoneNumber, String bankName, String bankAccount, String residentRegistrationNumber,
					int departmentID, int salary, Date dateOfemployment){
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

	public String getName() {
		return this.name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public String getEmploymentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.employmentDate);
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public ArrayList<Family> getFamilyList() {
		return familyList;
	}

	public void setFamilyList(ArrayList<Family> familyList) {
		this.familyList = familyList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<PaymentDetail> getPaymentDetailList() {
		return paymentDetailList;
	}

	public void setPaymentDetailList(ArrayList<PaymentDetail> paymentDetailList) {
		this.paymentDetailList = paymentDetailList;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public EmployeePosition getPosition() {
		return position;
	}

	public void setPosition(EmployeePosition position) {
		this.position = position;
	}

	public String getResidentRegistrationNumber() {
		return residentRegistrationNumber;
	}

	public void setResidentRegistrationNumber(String residentRegistrationNumber) {
		this.residentRegistrationNumber = residentRegistrationNumber;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}

