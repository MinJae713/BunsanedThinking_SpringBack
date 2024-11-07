package com.example.bunsanedthinking_springback.entity.department;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupplyList;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @author 占쏙옙占쏙옙환
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */
//2024-06-02 源����쁽
@NoArgsConstructor
public class Department implements Cloneable {

	private String headName;
	private int id;
	private String name;
	private OfficeSupplyList officeSupplyList; // 이거 필드값 바꿔야하지 않나...
	private String purpose;
	private String task;
	private ArrayList<Employee> employeeList;
	
	public static int DepartmentSerialNum = 910;

	public Department(String name, String task, String purpose, String headName){
		this.setName(name);
		this.setTask(task);
		this.setPurpose(purpose);
		this.setHeadName(headName);
		employeeList = new ArrayList<Employee>();
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OfficeSupplyList getOfficeSupplyList() {
		return officeSupplyList;
	}

	public void setOfficeSupplyList(OfficeSupplyList officeSupplyList) {
		this.officeSupplyList = officeSupplyList;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getEmployeeCount() {
		return employeeList.size();
	}
	
	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Department clone() {
		Department department = new Department(getName(), getTask(), getPurpose(), getHeadName());
		department.setId(getId());
		department.setOfficeSupplyList(getOfficeSupplyList());
		
		return department;
	}


}
