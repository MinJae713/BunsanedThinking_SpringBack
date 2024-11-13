package com.example.bunsanedthinking_springback.model.service.employee.compensation;

import com.example.bunsanedthinking_springback.dto.employee.compensation.RequestCompensationDTO;
import com.example.bunsanedthinking_springback.dto.employee.compensation.RequestInsuranceMoneyDTO;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyStatus;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceMoney.InsuranceMoneyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.paymentDetail.PaymentDetailEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.product.ProductEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompensationService {
	@Autowired
	private InsuranceMoneyEntityModel insuranceMoneyEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private ReportEntityModel reportEntityModel;
	@Autowired
	private PaymentDetailEntityModel paymentDetailEntityModel;
	@Autowired
	private AccidentEntityModel accidentEntityModel;
	@Autowired
	private ProductEntityModel productEntityModel;

	@Value("${serials.paymentDetail}")
	public Integer PAYMENT_DETAIL_SERIAL_NUMBER;

	public void requestCompensation(RequestCompensationDTO requestCompensationDTO)
		throws NotExistException, AlreadyProcessedException {
		String accountHolder = requestCompensationDTO.getAccountHolder();
		String bank = requestCompensationDTO.getBank();
		String bankAccount = requestCompensationDTO.getBankAccount();
		int money = requestCompensationDTO.getMoney();
		int paymentType = requestCompensationDTO.getPaymentType();
		int contractId = requestCompensationDTO.getContractId();
		int reportId = requestCompensationDTO.getReportId();

		Report report = reportEntityModel.getById(reportId);
		if (report == null)
			throw new NotExistException();
		if (contractEntityModel.getById(contractId) == null)
			throw new NotExistException();
		if (report.getProcessStatus() == ReportProcessStatus.Completed)
			throw new AlreadyProcessedException();
		int paymentId = NextIdGetter.getNextId(paymentDetailEntityModel.getMaxId(), PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail payment = new PaymentDetail(accountHolder, bank,
			bankAccount, money, PaymentType.values()[paymentType], contractId);
		payment.setId(paymentId);
		paymentDetailEntityModel.add(payment);
		report.setProcessStatus(ReportProcessStatus.Completed);
		reportEntityModel.update(report);
		report.getAccident().complete();
		accidentEntityModel.update(report.getAccident());

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

	public void requestInsuranceMoney(RequestInsuranceMoneyDTO requestInsuranceMoneyDTO) throws
		NotExistException,
		AlreadyProcessedException {

		int customerId = requestInsuranceMoneyDTO.getCustomerId();
		int money = requestInsuranceMoneyDTO.getMoney();
		int insuranceMoneyId = requestInsuranceMoneyDTO.getInsuranceMoneyId();
		int paymentType = requestInsuranceMoneyDTO.getPaymentType();
		int contractId = requestInsuranceMoneyDTO.getContractId();

		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null)
			throw new NotExistException();
		Customer customer = customerEntityModel.getById(customerId);
		if (customer == null)
			throw new NotExistException();
		if (customer.getId() != contract.getCustomerID())
			throw new NotExistException();
		InsuranceMoney insuranceMoney = insuranceMoneyEntityModel.getById(insuranceMoneyId);
		if (insuranceMoney == null)
			throw new NotExistException();
		if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed)
			throw new AlreadyProcessedException();
		int paymentId = NextIdGetter.getNextId(paymentDetailEntityModel.getMaxId(), PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail payment = new PaymentDetail(customer.getName(),
			customer.getBankName(), customer.getBankAccount(),
			money, PaymentType.values()[paymentType], contractId);
		payment.setId(paymentId);
		paymentDetailEntityModel.add(payment);
		insuranceMoney.handle();
		insuranceMoneyEntityModel.update(insuranceMoney);

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
		return insuranceMoneyEntityModel.getAll();
		//		return insuranceMoneyList.getAll();
	}

	public List<InsuranceMoney> getAllUnprocessedInsuranceMoney() {
		//		return getAllInsuranceMoney().stream()
		//			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Unprocessed)
		//			.toList();
		return insuranceMoneyEntityModel.getAll().stream()
			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Unprocessed)
			.toList();

	}

	public List<InsuranceMoney> getAllProcessedInsuranceMoney() {
		//		return getAllInsuranceMoney().stream()
		//			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Completed)
		//			.toList();
		return insuranceMoneyEntityModel.getAll().stream()
			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Completed)
			.toList();
	}

	public InsuranceMoney getInsuranceMoneyById(int id) throws NotExistException {
		//		InsuranceMoneyVO insuranceMoneyVO = insuranceMoneyMapper.getById_Compensation(id).orElse(null);
		//		if (insuranceMoneyVO == null)
		//			throw new NotExistException();
		//		return insuranceMoneyVO.getEntity();
		return insuranceMoneyEntityModel.getById(id);
	}

	// 여기부터 contract 하나 찾기
	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractEntityModel.getById(contractId);
		//		return contractList.get(contractId);
	}

	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerEntityModel.getById(id);
		//		return customerList.get(customerID);
	}

	public List<Report> getAllReport() {
		return reportEntityModel.getAll();
		//		return reportList.getAll();
	}

	public Report getReportById(int id) throws NotExistException {
		return reportEntityModel.getById(id);
		//		return reportList.get(id);
	}

	public List<Report> getAllUnprocessedReport() {
		return reportEntityModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == ReportProcessStatus.Unprocessed)
				.toList();
		//		return reportList.getAllUnprocessedReport();
	}

	public List<Report> getAllCompletedReport() {
		return reportEntityModel.getAll().stream()
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
		Customer customer = customerEntityModel.getById(customerID);
		if (customer == null)
			throw new NotExistException();
		List<Contract> customerContracts = customer.getContractList();
		if (customerContracts == null)
			throw new NotExistException();
		for (Contract contract : customerContracts) {
			Product product = productEntityModel.getById(contract.getProductId());
			if (product instanceof Automobile)
				return contract;
		}
		throw new NotExistContractException();
		//		return contractList.getContractByOneAutomobileId(customerID);
	}
}
