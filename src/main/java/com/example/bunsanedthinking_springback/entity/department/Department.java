package com.example.bunsanedthinking_springback.entity.department;

import java.util.ArrayList;
import java.util.List;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import com.example.bunsanedthinking_springback.vo.DepartmentVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 占쏙옙占쏙옙환
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */
//2024-06-02 源����쁽
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department implements Cloneable {
	private String headName;
	private int id;
	private String name;
	private List<OfficeSupply> officeSupplyList;
	private String purpose;
	private String task;
	private ArrayList<Employee> employeeList;

	public static int DepartmentSerialNum = 910;

	public Department(String name, String task, String purpose, String headName) {
		this.setName(name);
		this.setTask(task);
		this.setPurpose(purpose);
		this.setHeadName(headName);
		employeeList = new ArrayList<Employee>();
	}

	public DepartmentVO findVO() {
		return new DepartmentVO(id, headName, name, purpose, task);
	}

	public Department clone() {
		Department department = new Department(getName(), getTask(), getPurpose(), getHeadName());
		department.setId(getId());
		department.setOfficeSupplyList(getOfficeSupplyList());

		return department;
	}

}
