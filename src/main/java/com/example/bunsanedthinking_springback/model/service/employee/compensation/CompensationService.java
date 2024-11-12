package com.example.bunsanedthinking_springback.model.service.employee.compensation;

import com.example.bunsanedthinking_springback.dto.employee.compensation.ReqCompensationDTO;
import com.example.bunsanedthinking_springback.dto.employee.compensation.ReqInsuranceMoneyDTO;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentDModel;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceMoney.InsuranceMoneyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportDModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationService {
	@Autowired
	private InsuranceMoneyEntityModel insuranceMoneyDModel;
	@Autowired
	private ContractDModel contractDModel;
	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private ReportDModel reportDModel;
	@Autowired
	private PaymentDetailDModel paymentDetailDModel;
	@Autowired
	private AccidentDModel accidentDModel;

	public void requestCompensation(ReqCompensationDTO reqCompensationDTO)
		throws NotExistException, AlreadyProcessedException {
		String accountHolder = reqCompensationDTO.getAccountHolder();
		String bank = reqCompensationDTO.getBank();
		String bankAccount = reqCompensationDTO.getBankAccount();
		int money = reqCompensationDTO.getMoney();
		int paymentType = reqCompensationDTO.getPaymentType();
		int contractId = reqCompensationDTO.getContractId();
		int reportId = reqCompensationDTO.getReportId();

		Report report = reportDModel.getById(reportId);
		if (report == null) throw new NotExistException();
		if (contractDModel.getById(contractId) == null) throw new NotExistException();
		if (report.getProcessStatus() == ReportProcessStatus.Completed)
			throw new AlreadyProcessedException();
		int paymentId = paymentDetailDModel.getAll().isEmpty() ?
				Integer.parseInt(PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER+"1") :
				NextIdGetter.getNextId(paymentDetailDModel.getMaxId(), PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail payment = new PaymentDetail(accountHolder, bank,
				bankAccount, money, PaymentType.values()[paymentType], contractId);
		payment.setId(paymentId);
		paymentDetailDModel.add(payment);
		report.setProcessStatus(ReportProcessStatus.Completed);
		reportDModel.update(report);
		report.getAccident().complete();
		accidentDModel.update(report.getAccident());

		//		if (report.getProcessStatus() == ReportProcessStatus.Completed) {
		//			throw new AlreadyProcessedException();
		//		}
		//		PaymentDetail payment = new PaymentDetail(accountHolder, bank, bankAccount, money, paymentType, contractId);
		//		paymentDetailList.add(payment);
		//		report.setProcessStatus(ReportProcessStatus.Completed);
		//		reportList.update(report);
		//		report.getAccident().complete(); - accident의 processStatus를 Complete 변경
		//		accidentList.update(report.getAccident());
	}

	public void requestInsuranceMoney(ReqInsuranceMoneyDTO reqInsuranceMoneyDTO) throws
		NotExistException,
		AlreadyProcessedException {

		int customerId = reqInsuranceMoneyDTO.getCustomerId();
		int money = reqInsuranceMoneyDTO.getMoney();
		int insuranceMoneyId = reqInsuranceMoneyDTO.getInsuranceMoneyId();
		int paymentType = reqInsuranceMoneyDTO.getPaymentType();
		int contractId = reqInsuranceMoneyDTO.getContractId();

		Contract contract = contractDModel.getById(contractId);
		if (contract == null) throw new NotExistException();
		Customer customer = customerDModel.getById(customerId);
		if (customer == null) throw new NotExistException();
		if (customer.getId() != contract.getCustomerID())
			throw new NotExistException();
		InsuranceMoney insuranceMoney = insuranceMoneyDModel.getById(insuranceMoneyId);
		if (insuranceMoney == null) throw new NotExistException();
		if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed)
			throw new AlreadyProcessedException();
		int paymentId = paymentDetailDModel.getAll().isEmpty() ?
				Integer.parseInt(PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER+"1") :
				NextIdGetter.getNextId(paymentDetailDModel.getMaxId(), PaymentDetail.PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail payment = new PaymentDetail(customer.getName(),
				customer.getBankName(), customer.getBankAccount(),
				money, PaymentType.values()[paymentType], contractId);
		payment.setId(paymentId);
		paymentDetailDModel.add(payment);
		insuranceMoney.handle();
		insuranceMoneyDModel.update(insuranceMoney);

		//		if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed) {
		//			throw new AlreadyProcessedException();
		//		}
		//		PaymentDetail payment = new PaymentDetail(customer.getName(), customer.getBankName(), customer.getBankAccount(), money, paymentType, contractId);
		//		paymentDetailList.add(payment);
		//		insuranceMoney.setProcessStatus(InsuranceMoneyStatus.Completed);
		//		insuranceMoney.handle();
		//		insuranceMoneyList.update(insuranceMoney);
	}

	// 아래부터 get - 아래는 완료
	public List<InsuranceMoney> getAllInsuranceMoney() {
		return insuranceMoneyDModel.getAll();
		//		return insuranceMoneyList.getAll();
	}

	public List<InsuranceMoney> getAllUnprocessedInsuranceMoney() {
//		return getAllInsuranceMoney().stream()
//			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Unprocessed)
//			.toList();
		return insuranceMoneyDModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Unprocessed)
				.toList();

	}

	public List<InsuranceMoney> getAllProcessedInsuranceMoney() {
//		return getAllInsuranceMoney().stream()
//			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Completed)
//			.toList();
		return insuranceMoneyDModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Completed)
				.toList();
	}

	public InsuranceMoney getInsuranceMoneyById(int id) throws NotExistException {
//		InsuranceMoneyVO insuranceMoneyVO = insuranceMoneyMapper.getById_Compensation(id).orElse(null);
//		if (insuranceMoneyVO == null)
//			throw new NotExistException();
//		return insuranceMoneyVO.getEntity();
		return insuranceMoneyDModel.getById(id);
	}

	// 여기부터 contract 하나 찾기
	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractDModel.getById(contractId);
		//		return contractList.get(contractId);
	}
	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerDModel.getById(id);
		//		return customerList.get(customerID);
	}

	public List<Report> getAllReport() {
		return reportDModel.getAll();
		//		return reportList.getAll();
	}

	public Report getReportById(int id) throws NotExistException {
		return reportDModel.getById(id);
		//		return reportList.get(id);
	}

	public List<Report> getAllUnprocessedReport() {
		return reportDModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == ReportProcessStatus.Unprocessed)
				.toList();
		//		return reportList.getAllUnprocessedReport();
	}

	public List<Report> getAllCompletedReport() {
		return reportDModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == ReportProcessStatus.Completed)
				.toList();
		//		return reportList.getAllCompletedReport();
	}

	public Contract getAutomobileByCustomerId(int customerID) throws NotExistContractException, NotExistException {
//		List<ContractVO> contractVOS = contractMapper.getAllByCustomerId_Compensation(customerID);
//		for (ContractVO contractVO : contractVOS) {
//			Product product = getProductById(contractVO.getProduct_id());
//			if (product instanceof Automobile)
//				return getContractById(contractVO.getId());
//		}
//		throw new NotExistContractException();
		Customer customer = customerDModel.getById(customerID);
		if (customer == null) throw new NotExistException();
		List<Contract> customerContracts = customer.getContractList();
		if (customerContracts == null) throw new NotExistException();
		for (Contract contract : customerContracts)
			if (contract.getProduct() instanceof Automobile)
				return contract;
		throw new NotExistContractException();
		//		return contractList.getContractByOneAutomobileId(customerID);
	}
}
