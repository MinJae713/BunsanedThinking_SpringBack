package com.example.bunsanedthinking_springback.model.service.employee.underwriting;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.product.ProductEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UnderWritingService {

	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private ProductEntityModel productEntityModel;

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
			Product product = productEntityModel.getById(contract.getProductId());
			if (product != null) {
				contract.setExpirationDate(Date.from(LocalDate.now()
						.plusYears(((Insurance)product).getContractPeriod())
						.atStartOfDay(ZoneId.systemDefault())
						.toInstant()));
			}
			// 대현님 이거 로직 수정했심다...! 한번 검토해줍쇼
//			if (contract.getProduct() != null) {
//				contract.setExpirationDate(Date.from(LocalDate.now()
//					.plusYears(((Insurance)contract.getProduct()).getContractPeriod())
//					.atStartOfDay(ZoneId.systemDefault())
//					.toInstant()));
//			}
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
