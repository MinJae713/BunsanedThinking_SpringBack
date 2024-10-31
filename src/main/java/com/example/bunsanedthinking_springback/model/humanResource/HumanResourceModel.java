package com.example.bunsanedthinking_springback.model.humanResource;

import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.department.DepartmentList;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.EmployeeList;
import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.family.FamilyList;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class HumanResourceModel {

	public void addEmployee(int teamId, String name, EmployeePosition employeePosition, String address, String phoneNumber, String bankName, String bankAccount, String residentRegistrationNumber, int departmentID, int salary, String dateOfemployment, EmployeeList employeeList, ArrayList<Family> tempFamilyList, FamilyList familyList) throws DuplicateResidentRegistrationNumberException {
		for (Employee employee : employeeList.getAll()) {
			if (employee.getResidentRegistrationNumber().equals(residentRegistrationNumber)) {
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
		Employee employee = null;
		int[] teamIds = {110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230};
		for (int id : teamIds) {
			if (teamId == id) {
				employee = new Employee(name, employeePosition,address, phoneNumber, 
						bankName, bankAccount,residentRegistrationNumber, departmentID, salary,date);
				employeeList.add(employee);
			}
		}
		for(Family e:tempFamilyList) {
			e.setEmployeeID(employee.getId());
			familyList.add(e);
		}
		employee.setFamilyList(tempFamilyList);
	}

	public void deleteEmployee(EmployeeList employeeList, int id) throws NotExistException{
		employeeList.delete(id);
	}

	public Employee getEmployee(EmployeeList employeeList, int id) throws NotExistException{
		try {
			return employeeList.get(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 직원 정보가 존재하지 않습니다.");
		}
	}

	public void requestAdditionalAllowance(){
		System.out.println("Additional Allowance");
	}

	public void requestBenefit(){
		System.out.println("Request Benefit");
	}

	public void updateEmployee(int index, String input, Employee employee, EmployeeList employeeList) throws NotExistException {
		switch (index) {
		case 1:
			employee.setName(input);
			employeeList.update(employee);
			break;
		case 2:
			if (EmployeePosition.Intern.ordinal() == (Integer.parseInt(input) - 1)) {
				employee.setPosition(EmployeePosition.Intern);
			} else if (EmployeePosition.Staff.ordinal() == (Integer.parseInt(input) - 1)) {
				employee.setPosition(EmployeePosition.Staff);
			}else if (EmployeePosition.SeniorStaff.ordinal() == (Integer.parseInt(input) - 1)) {
				employee.setPosition(EmployeePosition.SeniorStaff);
			}else if (EmployeePosition.Manager.ordinal() == (Integer.parseInt(input) - 1)) {
				employee.setPosition(EmployeePosition.Manager);
			}else if (EmployeePosition.DeputyGeneralManager.ordinal() == (Integer.parseInt(input) - 1)) {
				employee.setPosition(EmployeePosition.DeputyGeneralManager);
			}else if (EmployeePosition.GeneralManager.ordinal() == (Integer.parseInt(input) - 1)) {
				employee.setPosition(EmployeePosition.GeneralManager);
			}
			employeeList.update(employee);
			break;
		case 3:
			employee.setAddress(input);
			employeeList.update(employee);
			break;
		case 4:
			employee.setPhoneNumber(input);
			employeeList.update(employee);
			break;
		case 5:
			employee.setBankName(input);
			employeeList.update(employee);
			break;
		case 6:
			employee.setBankAccount(input);
			employeeList.update(employee);
			break;
		case 8:
			employee.setDepartmentID(Integer.parseInt(input));
			employeeList.update(employee);
			break;
		case 9:
			employee.setSalary(Integer.parseInt(input));
			employeeList.update(employee);
			break;
		default:
			break;
		}
	}
	public ArrayList<Employee> getAll(EmployeeList employeeList) {
		return employeeList.getAll();
	}

	public ArrayList<Department> getAll(DepartmentList departmentList) {
		return departmentList.getAll();
	}

	public Department get(DepartmentList departmentList, int departmentID) throws NotExistException {
		return departmentList.get(departmentID);
	}
}