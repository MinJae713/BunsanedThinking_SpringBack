package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.underwriting.UnderWritingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/employee/underWriting")
public class UnderWritingController {
	@Autowired
	private UnderWritingModel underWritingModel;

	@PostMapping("/applyCoperation")
	public void applyCoperation() {
		underWritingModel.applyCoperation();
	}

	@PostMapping("/applyReinsurance")
	public void applyReinsurance() {
		underWritingModel.applyReinsurance();
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
		return underWritingModel.reviewAcquisition(contractId, result);
	}

	@GetMapping("/getAllRequestingInsurance")
	public ArrayList<Contract> getAllRequestingInsurance() throws
		NotExistException,
		IOException {
		return underWritingModel.getAllRequestingInsurance();
	}

	@GetMapping("/getCustomer")
	public Customer getCustomer(int id)  {
		return underWritingModel.getCustomer(id);
	}

	@GetMapping("/getContract")
	public Contract getContract(int id) throws
		NotExistException{
		return underWritingModel.getContract(id);
	}

	@GetMapping("/getAllNotRequestingInsurance")
	public ArrayList<Contract> getAllNotRequestingInsurance() throws
		NotExistException{
		return underWritingModel.getAllNotRequestingInsurance();
	}

}
