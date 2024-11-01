package com.example.bunsanedthinking_springback.model.underwriting;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.ContractMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UnderWritingModel {

	public void applyCoperation() {

	}

	public void applyReinsurance() {

	}

	public boolean reviewAcquisition(Contract contract, boolean result, ContractList contractList)
			throws AlreadyProcessedException, NotExistContractException {
		if (contract.getContractStatus() != ContractStatus.ContractRequesting) {
			throw new AlreadyProcessedException();
		}
		contract.review(result, contractList);
		return result;
	}
	public ArrayList<Contract> getAllRequestingInsurance(ContractList contractList){
		return contractList.getAllRequestingInsurance();
	}
	public Customer get(CustomerList customerList, int id) throws NotExistException {
		return customerList.get(id);
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return contractList.get(id);
	}
	public ArrayList<Contract> getAllNotRequestingInsurance(ContractList contractList){
		return contractList.getAllNotRequestingInsurance();
	}
}