package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.department.DepartmentList;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeeList;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.FamilyList;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.humanResource.HumanResourceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/humanResource")
public class HumanResourceController {
	@Autowired
	private HumanResourceModel humanResourceModel;

	public void addEmployee(int teamId, String name, EmployeePosition employeePosition, String address, String phoneNumber, String bankName, String bankAccount, String residentRegistrationNumber, int departmentID, int salary, String dateOfemployment, EmployeeList employeeList, ArrayList<Family> tempFamilyList, FamilyList familyList) throws DuplicateResidentRegistrationNumberException {
		humanResourceModel.addEmployee(teamId, name, employeePosition, address, phoneNumber, 
				bankName, bankAccount, residentRegistrationNumber, departmentID, salary, 
				dateOfemployment, employeeList, tempFamilyList, familyList);
	}

	public void deleteEmployee(EmployeeList employeeList, int id) throws NotExistException {
		humanResourceModel.deleteEmployee(employeeList, id);
	}

	public Employee getEmployee(EmployeeList employeeList, int id) throws NotExistException{
		return humanResourceModel.getEmployee(employeeList, id);
	}

	public void requestAdditionalAllowance(){
		humanResourceModel.requestAdditionalAllowance();
	}

	public void requestBenefit(){
		humanResourceModel.requestBenefit();
	}

	public void updateEmployee(int index, String input, Employee employee, EmployeeList employeeList) throws NotExistException {
		humanResourceModel.updateEmployee(index, input, employee, employeeList);
	}
	public ArrayList<Employee> getAll(EmployeeList employeeList) {
		return humanResourceModel.getAll(employeeList);
	}
	public ArrayList<Department> getAll(DepartmentList departmentList) {
		return humanResourceModel.getAll(departmentList);
	}
	public Department get(DepartmentList departmentList, int departmentID) throws NotExistException {
		return humanResourceModel.get(departmentList, departmentID);
	}
}
