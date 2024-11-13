package com.example.bunsanedthinking_springback.model.entityModel.contract;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.model.entityModel.compensationDetail.CompensationDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.depositDetail.DepositDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceMoney.InsuranceMoneyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContractEntityModel {
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private InsuranceMoneyEntityModel insuranceMoneyDModel;
	@Autowired
	private CompensationDetailEntityModel compensationDetailEntityModel;
	@Autowired
	private DepositDetailEntityModel depositDetailEntityModel;
	@Autowired
	private PaymentDetailEntityModel paymentDetailEntityModel;
//	@Autowired
//	private ProductEntityModel productEntityModel;

	public Contract getById(int id) {
		Optional<ContractVO> optionalContractVO = contractMapper.getById(id);
		if (optionalContractVO.isEmpty())
			return null;
		ContractVO contractVO = optionalContractVO.get();
		ArrayList<InsuranceMoney> insuranceMonies = new ArrayList<InsuranceMoney>();
		ArrayList<CompensationDetail> compensationDetails = new ArrayList<CompensationDetail>();
		ArrayList<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
		ArrayList<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();

		insuranceMoneyDModel.getAll().stream()
			.filter(e -> e.getContractID() == id)
			.forEach(insuranceMonies::add);
		compensationDetailEntityModel.getAll().stream()
			.filter(e -> e.getContractId() == id)
			.forEach(compensationDetails::add);
		depositDetailEntityModel.getAll().stream()
			.filter(e -> e.getContractId() == id)
			.forEach(depositDetails::add);
		paymentDetailEntityModel.getAll().stream()
			.filter(e -> e.getContractId() == id)
			.forEach(paymentDetails::add);
//		Product product = productEntityModel.getById(contractVO.getProduct_id());
		return new Contract(insuranceMonies, compensationDetails, depositDetails, paymentDetails, contractVO);
	}

	public List<Contract> getAll() {
		List<Contract> contracts = new ArrayList<>();
		contractMapper.getAll().forEach(e -> contracts.add(getById(e.getId())));
		return contracts;
	}

	public List<Contract> getAllRequestingInsurance() {
		List<Contract> contracts = new ArrayList<>();
		List<Contract> requestingInsurances = new ArrayList<>();
		contractMapper.getAll().forEach(e -> contracts.add(getById(e.getId())));
		for (Contract e : contracts) {
			if (e.getContractStatus() == ContractStatus.ContractRequesting) {
				requestingInsurances.add(e);
			}
		}
		return requestingInsurances;
	}

	public List<Contract> getAllNotRequestingInsurance() {
		List<Contract> contracts = new ArrayList<>();
		List<Contract> notRequestingInsurances = new ArrayList<>();
		contractMapper.getAll().forEach(e -> contracts.add(getById(e.getId())));
		for (Contract e : contracts) {
			if (e.getContractStatus() != ContractStatus.ContractRequesting) {
				notRequestingInsurances.add(e);
			}
		}
		return notRequestingInsurances;
	}

	public Integer getMaxId() {
		return contractMapper.getMaxId();
	}

	public void add(Contract contract) {
		if (contract == null)
			return;
		if (contractMapper.getById(contract.getId()).isPresent())
			return;
		contractMapper.insert(contract.findVO());

		List<InsuranceMoney> insuranceMonies = contract.getInsuranceMoneyList();
		if (insuranceMonies != null)
			insuranceMonies.forEach(e -> insuranceMoneyDModel.add(e));

		List<CompensationDetail> compensationDetails = contract.getCompensationDetailList();
		if (insuranceMonies != null) compensationDetails.forEach(e -> compensationDetailEntityModel.add(e));

		List<DepositDetail> depositDetails = contract.getDepositDetailList();
		if (depositDetails != null)
			depositDetails.forEach(e -> depositDetailEntityModel.add(e));

		List<PaymentDetail> paymentDetails = contract.getPaymentDetailList();
		if (paymentDetails != null) paymentDetails.forEach(e -> paymentDetailEntityModel.add(e));

//		Product product = contract.getProduct();
//		if (product != null) productEntityModel.add(product);
	}

	public void update(Contract contract) {
		if (contract == null)
			return;
		if (contractMapper.getById(contract.getId()).isEmpty())
			return;

		List<InsuranceMoney> insuranceMonies = contract.getInsuranceMoneyList();
		if (insuranceMonies != null)
			insuranceMonies.forEach(e -> insuranceMoneyDModel.update(e));

		List<CompensationDetail> compensationDetails = contract.getCompensationDetailList();
		if (insuranceMonies != null) compensationDetails.forEach(e -> compensationDetailEntityModel.update(e));

		List<DepositDetail> depositDetails = contract.getDepositDetailList();
		if (depositDetails != null)
			depositDetails.forEach(e -> depositDetailEntityModel.update(e));

		List<PaymentDetail> paymentDetails = contract.getPaymentDetailList();
		if (paymentDetails != null) paymentDetails.forEach(e -> paymentDetailEntityModel.update(e));

//		Product product = contract.getProduct();
//		if (product != null) productEntityModel.update(product);

		contractMapper.update(contract.findVO());
	}

	public void delete(int id) {
		if (contractMapper.getById(id).isEmpty())
			return;
		Contract contract = getById(id);

		List<InsuranceMoney> insuranceMonies = contract.getInsuranceMoneyList();
		if (insuranceMonies != null)
			insuranceMonies.forEach(e -> insuranceMoneyDModel.delete(e.getId()));

		List<CompensationDetail> compensationDetails = contract.getCompensationDetailList();
		if (insuranceMonies != null) compensationDetails.forEach(e -> compensationDetailEntityModel.delete(e.getId()));

		List<DepositDetail> depositDetails = contract.getDepositDetailList();
		if (depositDetails != null)
			depositDetails.forEach(e -> depositDetailEntityModel.delete(e.getId()));

		List<PaymentDetail> paymentDetails = contract.getPaymentDetailList();
		if (paymentDetails != null) paymentDetails.forEach(e -> paymentDetailEntityModel.delete(e.getId()));

//		Product product = contract.getProduct();
//		if (product != null) productEntityModel.delete(product.getId());

		contractMapper.deleteById(id);
	}
}
