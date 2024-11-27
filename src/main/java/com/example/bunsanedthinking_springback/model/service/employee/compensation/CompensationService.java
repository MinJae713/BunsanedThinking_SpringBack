package com.example.bunsanedthinking_springback.model.service.employee.compensation;

import com.example.bunsanedthinking_springback.dto.employee.compensation.request.CompensationRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.request.InsuranceMoneyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.RequestCompensationResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.RequestInsuranceMoneyDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.RequestInsuranceMoneyResponse;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
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
import java.util.stream.Collectors;

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

	public void requestCompensation(CompensationRequest compensationRequest)
            throws NotExistException, AlreadyProcessedException, NotExistContractException {
		int money = compensationRequest.getMoney();
		int paymentType = compensationRequest.getPaymentType();
		int reportId = compensationRequest.getReportId();
		Report report = reportEntityModel.getById(reportId);
		if (report == null) throw new NotExistException("해당 신고 정보가 존재하지 않습니다");
		int customerId = report.getAccident().getCustomerID();
		Customer customer = customerEntityModel.getById(customerId);
		if (customer == null) throw new NotExistException("해당 고객이 없습니다");
		String accountHolder = customer.getName();
		String bank = customer.getBankName();
		String bankAccount = customer.getBankAccount();
		Contract contract = getAutomobileByCustomerId(customerId);
		if (contract == null) throw new NotExistContractException();
		if (report.getProcessStatus() == ReportProcessStatus.Completed)
			throw new AlreadyProcessedException();
		int paymentId = NextIdGetter.getNextId(paymentDetailEntityModel.getMaxId(), PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail payment = new PaymentDetail(accountHolder, bank,
			bankAccount, money, PaymentType.values()[paymentType], contract.getId());
		payment.setId(paymentId);
		paymentDetailEntityModel.add(payment);
		report.setProcessStatus(ReportProcessStatus.Completed);
		reportEntityModel.update(report);
		report.getAccident().complete();
		accidentEntityModel.update(report.getAccident());
	}

	public void requestInsuranceMoney(InsuranceMoneyRequest insuranceMoneyRequest) throws
            NotExistException,
            AlreadyProcessedException, NotExistContractException {
		int money = insuranceMoneyRequest.getMoney();
		int insuranceMoneyId = insuranceMoneyRequest.getInsuranceMoneyId();
		int paymentType = insuranceMoneyRequest.getPaymentType();
		InsuranceMoney insuranceMoney = insuranceMoneyEntityModel.getById(insuranceMoneyId);
		if (insuranceMoney == null) throw new NotExistException("해당 보험금이 없습니다");
		if (insuranceMoney.getProcessStatus() == InsuranceMoneyStatus.Completed)
			throw new AlreadyProcessedException();
		Contract contract = contractEntityModel.getById(insuranceMoney.getContractID());
		if (contract == null) throw new NotExistContractException();
		Customer customer = customerEntityModel.getById(contract.getCustomerID());
		if (customer == null) throw new NotExistException("해당 고객이 없습니다");
		if (customer.getId() != contract.getCustomerID()) throw new NotExistException();
		int paymentId = NextIdGetter.getNextId(paymentDetailEntityModel.getMaxId(), PAYMENT_DETAIL_SERIAL_NUMBER);
		PaymentDetail payment = new PaymentDetail(customer.getName(),
			customer.getBankName(), customer.getBankAccount(),
			money, PaymentType.values()[paymentType], contract.getId());
		payment.setId(paymentId);
		paymentDetailEntityModel.add(payment);
		insuranceMoney.handle();
		insuranceMoneyEntityModel.update(insuranceMoney);
	}

	public List<RequestInsuranceMoneyResponse> getAllInsuranceMoney() {
		return insuranceMoneyEntityModel.getAll().stream()
				.map(this::getOneInsuranceMoney)
				.collect(Collectors.toList());
	}

	public List<RequestInsuranceMoneyResponse> getAllUnprocessedInsuranceMoney() {
		return insuranceMoneyEntityModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Unprocessed)
				.map(this::getOneInsuranceMoney)
				.collect(Collectors.toList());

	}

	public List<RequestInsuranceMoneyResponse> getAllProcessedInsuranceMoney() {
		return insuranceMoneyEntityModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Completed)
				.map(this::getOneInsuranceMoney)
				.collect(Collectors.toList());
	}

	private RequestInsuranceMoneyResponse getOneInsuranceMoney(InsuranceMoney insuranceMoney) {
		Contract contract = contractEntityModel.getById(insuranceMoney.getContractID());
		Product product = productEntityModel.getById(contract.getProductId());
		Customer customer = customerEntityModel.getById(contract.getCustomerID());
		return RequestInsuranceMoneyResponse.of(insuranceMoney, product, customer);
	}

	public RequestInsuranceMoneyDetailResponse getInsuranceMoneyById(int id) throws NotExistException, NotExistContractException {
		InsuranceMoney insuranceMoney = insuranceMoneyEntityModel.getById(id);
		Contract contract = contractEntityModel.getById(insuranceMoney.getContractID());
		if (contract == null) throw new NotExistContractException();
		Customer customer = customerEntityModel.getById(contract.getCustomerID());
		if (customer == null) throw new NotExistException("해당 고객이 존재하지 않습니다");
		Product product = productEntityModel.getById(contract.getProductId());
		if (!(product instanceof Insurance insurance)) throw new NotExistException("해당 상품이 존재하지 않습니다");
		return RequestInsuranceMoneyDetailResponse.of(insuranceMoney, insurance, customer);
	}
	public RequestInsuranceMoneyResponse getInsuranceMoneyRowById(int id) throws NotExistException {
		InsuranceMoney insuranceMoney = insuranceMoneyEntityModel.getById(id);
		if (insuranceMoney == null) throw new NotExistException("해당 보험금이 없습니다");
		return getOneInsuranceMoney(insuranceMoney);
	}
	public Contract getContractById(int contractId) throws NotExistContractException {
		Contract contract = contractEntityModel.getById(contractId);
		if (contract == null) throw new NotExistContractException();
		return contract;
	}

	public Customer getCustomerById(int id) throws NotExistException {
		Customer customer = customerEntityModel.getById(id);
		if (customer == null) throw new NotExistException("해당 고객이 없습니다");
		return customer;
	}

	public List<RequestCompensationResponse> getAllReport() {
		return reportEntityModel.getAll().stream()
				.map(RequestCompensationResponse::of)
				.collect(Collectors.toList());
	}

	public Report getReportById(int id) throws NotExistException {
		Report report = reportEntityModel.getById(id);
		if (report == null) throw new NotExistException("해당 신고가 없습니다");
		return report;
	}

	public RequestCompensationResponse getReportRowById(int id) throws NotExistException {
		return RequestCompensationResponse.of(getReportById(id));
	}

	public List<RequestCompensationResponse> getAllUnprocessedReport() {
		return reportEntityModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == ReportProcessStatus.Unprocessed)
				.map(RequestCompensationResponse::of)
				.collect(Collectors.toList());
	}

	public List<RequestCompensationResponse> getAllCompletedReport() {
		return reportEntityModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == ReportProcessStatus.Completed)
				.map(RequestCompensationResponse::of)
				.collect(Collectors.toList());
	}

	public Contract getAutomobileByCustomerId(int customerID) throws NotExistContractException, NotExistException {
		Customer customer = customerEntityModel.getById(customerID);
		if (customer == null)
			throw new NotExistException("해당 고객이 없습니다");
		List<Contract> customerContracts = customer.getContractList();
		if (customerContracts == null)
			throw new NotExistException("해당 고객이 가입한 계약이 없습니다");
		for (Contract contract : customerContracts) {
			Product product = productEntityModel.getById(contract.getProductId());
			if (product instanceof Automobile)
				return contract;
		}
		throw new NotExistContractException();
	}
}
