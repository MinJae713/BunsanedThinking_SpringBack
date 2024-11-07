package com.example.bunsanedthinking_springback.model.domain.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.model.domain.compensationDetail.CompensationDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.depositDetail.DepositDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.insuranceMoney.InsuranceMoneyDModel;
import com.example.bunsanedthinking_springback.model.domain.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.model.domain.product.ProductDModel;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.vo.ContractVO;

@Service
public class ContractDModel {
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private InsuranceMoneyDModel insuranceMoneyDModel;
	@Autowired
	private CompensationDetailDModel compensationDetailDModel;
	@Autowired
	private DepositDetailDModel depositDetailDModel;
	@Autowired
	private PaymentDetailDModel paymentDetailDModel;
	@Autowired
	private ProductDModel productDModel;

	public Contract getById(int id) {
		Optional<ContractVO> optionalContractVO = contractMapper.getById_Customer(id);
		if (optionalContractVO.isEmpty())
			return null;
		ContractVO contractVO = optionalContractVO.get();
		ArrayList<InsuranceMoney> insuranceMonies = new ArrayList<InsuranceMoney>();
		ArrayList<CompensationDetail> compensationDetails = new ArrayList<CompensationDetail>();
		ArrayList<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
		ArrayList<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();

		insuranceMoneyDModel.getAll().stream()
			.filter(e -> e.getContractId() == id)
			.forEach(insuranceMonies::add);
		compensationDetailDModel.getAll().stream()
			.filter(e -> e.getContractId() == id)
			.forEach(compensationDetails::add);
		depositDetailDModel.getAll().stream()
			.filter(e -> e.getContractId() == id)
			.forEach(depositDetails::add);
		paymentDetailDModel.getAll().stream()
			.filter(e -> e.getContractId() == id)
			.forEach(paymentDetails::add);
		Product product = productDModel.getById(id);
		return new Contract(insuranceMonies, compensationDetails, depositDetails, paymentDetails, product, contractVO);
	}

	public List<Contract> getAll() {
		List<Contract> contracts = new ArrayList<Contract>();
		contractMapper.getAll_Customer().forEach(e -> contracts.add(getById(e.getId())));
		return contracts;
	}

	public Integer getMaxId() {
		return contractMapper.getMaxId_SalesModel();
	}

	public void add(ContractVO contractVO) {
		contractMapper.insert_SalesModel(contractVO);
	}

	public void update(ContractVO contractVO) {
		contractMapper.update(contractVO);
	}

	public void delete(int id) {
		contractMapper.deleteById(id);
	}
}
