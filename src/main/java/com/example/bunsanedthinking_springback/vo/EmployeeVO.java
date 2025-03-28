package com.example.bunsanedthinking_springback.vo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeVO {
	private int id;
	private String address;
	private String bank_name;
	private String bank_account;
	private LocalDate employment_date;
	private String name;
	private String phone_number;
	private int position;
	private String resident_registration_number;
	private int salary;
	private int department_id;

	public Employee getEntity(
		Supplier<List<Family>> families,
		Supplier<List<PaymentDetail>> paymentDetails,
		Supplier<List<Contract>> contracts) {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setAddress(address);
		employee.setBankName(bank_name);
		employee.setBankAccount(bank_account);
		employee.setEmploymentDate(Date.valueOf(employment_date));
		employee.setName(name);
		employee.setPhoneNumber(phone_number);
		employee.setPosition(EmployeePosition.values()[position]);
		employee.setResidentRegistrationNumber(resident_registration_number);
		employee.setSalary(salary);
		employee.setDepartmentId(department_id);
		// employee.setFamilyList(families);
		// employee.setPaymentDetailList(paymentDetails);
		// employee.setContractList(contracts);
		employee.setFamilySupplier(families);
		employee.setPaymentDetailSupplier(paymentDetails);
		employee.setContractSupplier(contracts);
		return employee;
	}
}
