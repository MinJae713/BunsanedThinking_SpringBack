package com.example.bunsanedthinking_springback.model.service.employee.underwriting;

import com.example.bunsanedthinking_springback.dto.employee.underwriting.response.ReviewAcquisitionAccidentHistoryDetailResponse.ReviewAcquisitionDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.underwriting.response.ReviewAcquisitionResponse;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
			contract.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			int day = LocalDate.now().getDayOfMonth();
			if (29 <= day) contract.setPaymentDate(28); // 납부일 날짜 지정?!
			else contract.setPaymentDate(day);
			contract.setContractStatus(ContractStatus.Maintaining);
		} else {
			contract.setContractStatus(ContractStatus.Terminating);
		}
		contractEntityModel.update(contract);
		return result;
	}

	public List<ReviewAcquisitionResponse> getAllRequestingInsurance() {
		List<Contract> requestingInsurances = contractEntityModel.getAllRequestingInsurance();
		return requestingInsurances.stream()
			.map(e -> ReviewAcquisitionResponse.of(customerEntityModel.getById(e.getCustomerID()),e))
			.collect(Collectors.toList());
	}

	public List<ReviewAcquisitionResponse> getAllNotRequestingInsurance() {
		List<Contract> requestingInsurances = contractEntityModel.getAllNotRequestingInsurance();
		return requestingInsurances.stream()
			.map(e -> ReviewAcquisitionResponse.of(customerEntityModel.getById(e.getCustomerID()),e))
			.collect(Collectors.toList());
	}

	public List<ReviewAcquisitionResponse> getAllContract() {
		List<Contract> contracts = contractEntityModel.getAll();
		return contracts.stream()
			.map(e -> ReviewAcquisitionResponse.of(customerEntityModel.getById(e.getCustomerID()),e))
			.collect(Collectors.toList());
	}

	public Customer getCustomer(int id) {
		return customerEntityModel.getById(id);
	}

	public ReviewAcquisitionDetailResponse getContractDetail(int id) {
		Contract contract = contractEntityModel.getById(id);
		return ReviewAcquisitionDetailResponse.of(customerEntityModel.getById(contract.getCustomerID()),contract);
	}

	public ReviewAcquisitionResponse getContract(int id) {
		Contract contract = contractEntityModel.getById(id);
		return ReviewAcquisitionResponse.of(customerEntityModel.getById(contract.getCustomerID()),contract);
	}
}
