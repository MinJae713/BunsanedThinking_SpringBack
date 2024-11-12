package com.example.bunsanedthinking_springback.model.entityModel.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.family.FamilyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;
import com.example.bunsanedthinking_springback.repository.EmployeeMapper;
import com.example.bunsanedthinking_springback.vo.EmployeeVO;

@Service
public class EmployeeEntityModel {
	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private FamilyEntityModel familyDModel;
	@Autowired
	private PaymentDetailEntityModel paymentDetailEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;

	public Employee getById(int id) {
		EmployeeVO employeeVO = employeeMapper.getById(id).orElse(null);
		if (employeeVO == null)
			return null;
		ArrayList<Family> families = new ArrayList<Family>();
		ArrayList<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
		ArrayList<Contract> contracts = new ArrayList<Contract>();
		familyDModel.getAll()
			.stream()
			.filter(e -> e.getEmployeeID() == id)
			.forEach(families::add);
		paymentDetailEntityModel.getAll()
			.stream()
			.filter(e -> e.getEmployeeId() != null && e.getEmployeeId() == id)
			.forEach(paymentDetails::add);
		contractEntityModel.getAll()
			.stream()
			.filter(e -> e.getEmployeeID() != null && e.getEmployeeID() == id)
			.forEach(contracts::add);
		return employeeVO.getEntity(families, paymentDetails, contracts);
	}

	public List<Employee> getAll() {
		List<Employee> employees = new ArrayList<Employee>();
		employeeMapper.getAll().stream().forEach(e -> employees.add(getById(e.getId())));
		return employees;
	}

	public Integer getMaxId() {
		return employeeMapper.getMaxId();
	}

	public void add(Employee employee) {
		if (employee == null)
			return;
		if (employeeMapper.getById(employee.getId()).isPresent())
			return;
		employeeMapper.insert(employee.findEmployeeVO());

		List<Family> families = employee.getFamilyList();
		if (families != null)
			families.forEach(e -> familyDModel.add(e));

		List<PaymentDetail> paymentDetails = employee.getPaymentDetailList();
		if (paymentDetails != null)
			paymentDetails.forEach(e -> paymentDetailEntityModel.add(e));

		List<Contract> contracts = employee.getContractList();
		if (contracts != null)
			contracts.forEach(e -> contractEntityModel.add(e));
	}

	public void update(Employee employee) {
		if (employee == null)
			return;
		if (employeeMapper.getById(employee.getId()).isEmpty())
			return;

		List<Family> families = employee.getFamilyList();
		if (families != null)
			families.forEach(e -> familyDModel.update(e));

		List<PaymentDetail> paymentDetails = employee.getPaymentDetailList();
		if (paymentDetails != null)
			paymentDetails.forEach(e -> paymentDetailEntityModel.update(e));

		List<Contract> contracts = employee.getContractList();
		if (contracts != null)
			contracts.forEach(e -> contractEntityModel.update(e));

		employeeMapper.update(employee.findEmployeeVO());
	}

	public void delete(int id) {
		if (employeeMapper.getById(id).isEmpty())
			return;
		Employee employee = getById(id);

		List<Family> families = employee.getFamilyList();
		if (families != null)
			families.forEach(e -> familyDModel.delete(e.getId()));

		List<PaymentDetail> paymentDetails = employee.getPaymentDetailList();
		if (paymentDetails != null)
			paymentDetails.forEach(e -> paymentDetailEntityModel.delete(e.getId()));

		List<Contract> contracts = employee.getContractList();
		if (contracts != null)
			contracts.forEach(e -> contractEntityModel.delete(e.getId()));

		employeeMapper.deleteById(id);
	}
}
