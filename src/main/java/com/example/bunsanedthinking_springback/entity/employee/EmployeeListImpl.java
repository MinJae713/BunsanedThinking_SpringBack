package com.example.bunsanedthinking_springback.entity.employee;

import java.util.ArrayList;

import com.example.bunsanedthinking_springback.global.constants.DumyObjs;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;

/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */

//2024-06-02 김대현
//2024-06-04 김대현
public class EmployeeListImpl implements EmployeeList {
	public enum EDeparts {
		// 임시로 달아둠 - 디비 붙이면 지우십셔
		FINANCIAL_ACCOUNTANT(110),
		ADMINISTRATIVE(120),
		COMPENSATION(130),
		CUSTOMER_SUPPORT(140),
		MANAGEMENT_PLANNING(150),
		HUMANRESOURCE(160),
		CUSTOMER_INFORMATION_MANAGEMENT(170),
		SALES(180),
		PRODUCT_MANAGEMENT(190),
		UNDERWRITING(200),
		COMPENSATION_PLANNING(210),
		CONTRACT_MANAGEMENT(220),
		LOAN_MANAGEMENT(230);
		private int serial;

		private EDeparts(int serial) {
			this.serial = serial;
		}

		public int getSerial() {
			return serial;
		}

		public void setSerial(int serial) {
			this.serial = serial;
		}
	}

	private ArrayList<Employee> employeeList;
	public static int index = 0;
	private int eCounter = 0; // enum 값 반환용도(임시)

	public EmployeeListImpl() {
		employeeList = new ArrayList<Employee>();
		Employee[] dumy = DumyObjs.DUMY_EMPLOYEES;
		for (Employee employee : dumy)
			this.add(employee);
		//		System.out.println("==직원 초기화 목록==");
		//		for (Employee e : employeeList)
		//			System.out.println(e.getName()+", "+e.getId());
	}

	/**
	 *
	 * @param employee
	 */
	public void add(Employee employee) {
		index++;
		String compound = null;
		// 원래 코드
		//		if (employee instanceof FinancialAccountantModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + ""
		//					+ FinancialAccountantModel.FINANCIAL_ACCOUNTANT_ACCOUNTANT_SERIAL_NUMBER + "" + index;
		//		} else if (employee instanceof AdministrativeModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + AdministrativeModel.ADMINISTRATIVE_SERIAL_NUMBER + "" + index;
		//		} else if (employee instanceof CompensationModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + CompensationModel.COMPENSATION_SERIAL_NUMBER + "" + index;
		//		} else if (employee instanceof CustomerSupportModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + CustomerSupportModel.CUSTOMER_SUPPORT_SERIAL_NUMBER + ""
		//					+ index;
		//		} else if (employee instanceof ManagementPlanningModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + ManagementPlanningModel.MANAGEMENT_PLANNING_SERIAL_NUMBER + ""
		//					+ index;
		//		} else if (employee instanceof HumanResourceModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + HumanResourceModel.HUMANRESOURCE_SERIAL_NUMBER + "" + index;
		//		} else if (employee instanceof CustomerInformationManagementModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + ""
		//					+ CustomerInformationManagementModel.CUSTOMER_INFORMATION_MANAGEMENT_SERIAL_NUMBER + "" + index;
		//		} else if (employee instanceof Sales) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + Sales.SALES_SERIAL_NUMBER + "" + index;
		//		} else if (employee instanceof ProductManagementModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + ProductManagementModel.PRODUCT_MANAGEMENT_SERIAL_NUMBER + ""
		//					+ index;
		//		} else if (employee instanceof UnderwritingModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + UnderwritingModel.UNDERWRITING_SERIAL_NUMBER + "" + index;
		//		} else if (employee instanceof CompensationPlanningModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + CompensationPlanningModel.COMPENSATION_PLANNING_SERIAL_NUMBER
		//					+ "" + index;
		//		} else if (employee instanceof ContractManagementModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + ContractManagementModel.CONTRACT_MANAGEMENT_SERIAL_NUMBER + ""
		//					+ index;
		//		} else if (employee instanceof LoanManagementModel) {
		//			compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + LoanManagementModel.LOAN_MANAGEMENT_SERIAL_NUMBER + "" + index;
		//		}
		EDeparts departs = EDeparts.values()[eCounter % EDeparts.values().length];
		compound = Employee.EMPLOYEE_SERIAL_NUMBER + "" + departs.getSerial() + "" + index;
		employee.setId(Integer.parseInt(compound));
		employeeList.add(employee);
		eCounter++;
	}

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public void delete(int id) throws NotExistException {
		for (Employee e : employeeList) {
			if (e != null && e.getId() == id) {
				employeeList.remove(e);
				return;
			}
		}
		throw new NotExistException();
	}

	/**
	 *
	 * @param id
	 * @throws NotExistException
	 */
	public Employee get(int id) throws NotExistException {
		for (Employee employee : employeeList)
			if (employee.getId() == id)
				return employee;
		throw new NotExistException();
	}

	public void update(Employee employee) throws NotExistException {
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getId() == employee.getId()) {
				employeeList.set(i, employee);
				return;
			}
		}
		throw new NotExistException();
	}

	@Override
	public ArrayList<Employee> getAll() {
		return employeeList;
	}

	@Override
	public Sales getSales(int id) throws NotExistException {
		for (Employee employee : employeeList) {
			if (employee.getId() == id) {
				if (employee instanceof Sales)
					return (Sales)employee;
				else
					throw new NotExistException();
			}
		}
		throw new NotExistException();
	}

}
