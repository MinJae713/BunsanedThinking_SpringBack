package com.example.bunsanedthinking_springback.entity.employee;

import java.util.Date;

import com.example.bunsanedthinking_springback.vo.SalesVO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 환
 * @version 1.0
 * @created 27-5-2024      4:40:44
 */
//2024-05-31 紐⑥��솚
//2024-06-06 源����쁽
@NoArgsConstructor
@Data
public class Sales extends Employee {

	private int evaluate;
	private int contractCount;

	public Sales(String name, EmployeePosition employeePosition, String address, String phoneNumber, String bankName,
		String bankAccount, String residentRegistrationNumber, int departmentID, int salary,
		Date dateOfemployment) {
		super(name, employeePosition, address, phoneNumber, bankName, bankAccount, residentRegistrationNumber,
			departmentID,
			salary, dateOfemployment);
		evaluate = 0;
		contractCount = 0;
	}

	public Sales(Employee employee, int evaluate, int contractCount) {
		super(employee);
		this.evaluate = evaluate;
		this.contractCount = contractCount;
	}

	public SalesVO findVO() {
		return new SalesVO(getId(), evaluate, contractCount);
	}

	@Override
	public Employee clone() {
		Employee employee = new Sales(getName(), getPosition(), getAddress(), getPhoneNumber(), getBankName(),
			getBankAccount(), getResidentRegistrationNumber(), getDepartmentID(), getSalary(), this.employmentDate);
		employee.setId(getId());
		return employee;
	}
}
