package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.model.service.employee.underwriting.UnderWritingSModel;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/underWriting")
public class UnderWritingController {
	@Autowired
	private UnderWritingSModel underWritingSModel;

	@PostMapping("/applyCoperation")
	public void applyCoperation() {
		underWritingSModel.applyCoperation();
	}

	@PostMapping("/applyReinsurance")
	public void applyReinsurance() {
		underWritingSModel.applyReinsurance();
	}

	// public boolean reviewAcquisition(Contract contract, boolean result, ContractList contractList)
	// 	throws AlreadyProcessedException, NotExistContractException {
	// 	return underWritingModel.reviewAcquisition(contract, result, contractList);
	// }
	// public ArrayList<Contract> getAllRequestingInsurance(ContractList contractList) throws
	// 	NotExistException,
	// 	IOException {
	// 	return underWritingModel.getAllRequestingInsurance(contractList);
	// }
	// public Customer get(CustomerList customerList, int id) throws NotExistException {
	// 	return underWritingModel.get(customerList, id);
	// }
	// public Contract get(ContractList contractList, int id) throws
	// 	NotExistContractException,
	// 	NotExistException,
	// 	IOException {
	// 	return underWritingModel.get(contractList, id);
	// }
	// public ArrayList<Contract> getAllNotRequestingInsurance(ContractList contractList) throws
	// 	NotExistException,
	// 	IOException {
	// 	return underWritingModel.getAllNotRequestingInsurance(contractList);
	// }

	@PatchMapping("/reviewAcquisition")
	public boolean reviewAcquisition(int contractId, boolean result)
		throws AlreadyProcessedException {
		return underWritingSModel.reviewAcquisition(contractId, result);
	}

	@GetMapping("/getAllRequestingInsurance")
	public ArrayList<Contract> getAllRequestingInsurance() throws
		NotExistException{
		return underWritingSModel.getAllRequestingInsurance();
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(int id)  {
		return underWritingSModel.getCustomer(id);
	}

	@GetMapping("/getContract")
	public Contract getContract(int id) throws
		NotExistException{
		return underWritingSModel.getContract(id);
	}

	@GetMapping("/getAllNotRequestingInsurance")
	public ArrayList<Contract> getAllNotRequestingInsurance() throws
		NotExistException{
		return underWritingSModel.getAllNotRequestingInsurance();
	}

}
