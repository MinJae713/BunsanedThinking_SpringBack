package com.example.bunsanedthinking_springback.model.service.employee.underwriting;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;

@Service
public class UnderWritingService {

	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;

	public void applyCoperation() {

	}

	public void applyReinsurance() {

	}

	public boolean reviewAcquisition(int contractId, boolean result) throws AlreadyProcessedException {
		Contract contract = contractEntityModel.getById(contractId);
		if (contract.getContractStatus() != ContractStatus.ContractRequesting) {
			throw new AlreadyProcessedException();
		}
		if (result) {
			if (contract.getProduct() != null) {
				contract.setExpirationDate(Date.from(LocalDate.now()
					.plusYears(((Insurance)contract.getProduct()).getContractPeriod())
					.atStartOfDay(ZoneId.systemDefault())
					.toInstant()));
			}
			contract.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			contract.setContractStatus(ContractStatus.Maintaining);
		} else {
			contract.setContractStatus(ContractStatus.Terminating);
		}
		contractEntityModel.update(contract);
		return result;
	}

	public ArrayList<Contract> getAllRequestingInsurance() {
		return (ArrayList<Contract>)contractEntityModel.getAllRequestingInsurance();
	}

	public ArrayList<Contract> getAllNotRequestingInsurance() {
		return (ArrayList<Contract>)contractEntityModel.getAllNotRequestingInsurance();
	}

	public Customer getCustomer(int id) {
		return customerEntityModel.getById(id);
	}

	public Contract getContract(int id) {
		return contractEntityModel.getById(id);
	}

}
