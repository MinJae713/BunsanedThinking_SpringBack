package com.example.bunsanedthinking_springback.model.entityModel.employee;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.entityModel.family.FamilyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.repository.EmployeeMapper;
import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDModel {
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private FamilyEntityModel familyDModel;
	@Autowired
	private PaymentDetailDModel paymentDetailDModel;
	@Autowired
	private ContractDModel contractDModel;

	public Employee getById(int id) {
		EmployeeVO employeeVO = employeeMapper.findById_HumanResource(id).orElse(null);
		if (employeeVO == null)
			return null;
		ArrayList<Family> families = new ArrayList<Family>();
		ArrayList<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
		ArrayList<Contract> contracts = new ArrayList<Contract>();
		familyDModel.getAll().stream().filter(e -> e.getEmployeeID() == id).forEach(e -> families.add(e));
		paymentDetailDModel.getAll().stream().filter(e -> e.getEmployeeId() == id).forEach(e -> paymentDetails.add(e));
		contractDModel.getAll().stream().filter(e -> e.getEmployeeID() == id).forEach(e -> contracts.add(e));
		return employeeVO.getEntity(families, paymentDetails, contracts);
	}

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		employeeMapper.getAll_HumanResource().stream().forEach(e -> employees.add(getById(e.getId())));
		return employees;
	}

	public Integer getMaxId() {
		return employeeMapper.getMaxId();
	}

	public void add(Employee employee) {
		if (employee == null) return;
		if (employeeMapper.findById_HumanResource(employee.getId()).isPresent()) return;
		employeeMapper.insert_HumanResource(employee.findEmployeeVO());

		List<Family> families = employee.getFamilyList();
		if (families != null) families.forEach(e -> familyDModel.add(e));

		List<PaymentDetail> paymentDetails = employee.getPaymentDetailList();
		if (paymentDetails != null) paymentDetails.forEach(e -> paymentDetailDModel.add(e));

		List<Contract> contracts = employee.getContractList();
		if (contracts != null) contracts.forEach(e -> contractDModel.add(e));
	}

	public void update(Employee employee) {
		if (employee == null) return;
		if (employeeMapper.findById_HumanResource(employee.getId()).isEmpty()) return;

		List<Family> families = employee.getFamilyList();
		if (families != null) families.forEach(e -> familyDModel.update(e));

		List<PaymentDetail> paymentDetails = employee.getPaymentDetailList();
		if (paymentDetails != null) paymentDetails.forEach(e -> paymentDetailDModel.update(e));

		List<Contract> contracts = employee.getContractList();
		if (contracts != null) contracts.forEach(e -> contractDModel.update(e));

		employeeMapper.update_HumanResource(employee.findEmployeeVO());
	}

	public void delete(int id) {
		if (employeeMapper.findById_HumanResource(id).isEmpty()) return;
		Employee employee = getById(id);

		List<Family> families = employee.getFamilyList();
		if (families != null) families.forEach(e -> familyDModel.delete(e.getId()));

		List<PaymentDetail> paymentDetails = employee.getPaymentDetailList();
		if (paymentDetails != null) paymentDetails.forEach(e -> paymentDetailDModel.delete(e.getId()));

		List<Contract> contracts = employee.getContractList();
		if (contracts != null) contracts.forEach(e -> contractDModel.delete(e.getId()));

		employeeMapper.delete_HumanResource(id);
	}
}
