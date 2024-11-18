package com.example.bunsanedthinking_springback.model.service.employee.compensation;

import com.example.bunsanedthinking_springback.dto.employee.compensation.request.CompensationRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.request.InsuranceMoneyRequest;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.GetAllInsuranceMoneyResponse;
import com.example.bunsanedthinking_springback.dto.employee.compensation.response.GetAllReportResponse;
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

import java.util.ArrayList;
import java.util.Date;
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

	public void requestCompensation(CompensationRequest compensationRequest)
		throws NotExistException, AlreadyProcessedException {
		String accountHolder = compensationRequest.getAccountHolder();
		String bank = compensationRequest.getBank();
		String bankAccount = compensationRequest.getBankAccount();
		int money = compensationRequest.getMoney();
		int paymentType = compensationRequest.getPaymentType();
		int contractId = compensationRequest.getContractId();
		int reportId = compensationRequest.getReportId();

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
	}

	public void requestInsuranceMoney(InsuranceMoneyRequest insuranceMoneyRequest) throws
		NotExistException,
		AlreadyProcessedException {

		int customerId = insuranceMoneyRequest.getCustomerId();
		int money = insuranceMoneyRequest.getMoney();
		int insuranceMoneyId = insuranceMoneyRequest.getInsuranceMoneyId();
		int paymentType = insuranceMoneyRequest.getPaymentType();
		int contractId = insuranceMoneyRequest.getContractId();

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
	}

	public List<GetAllInsuranceMoneyResponse> getAllInsuranceMoney() {
		List<GetAllInsuranceMoneyResponse> result = new ArrayList<GetAllInsuranceMoneyResponse>();
		for (InsuranceMoney insuranceMoney : insuranceMoneyEntityModel.getAll()) {
			Contract contract = contractEntityModel.getById(insuranceMoney.getContractID());
			Product product = productEntityModel.getById(contract.getProductId());
			Customer customer = customerEntityModel.getById(contract.getCustomerID());

			int id = insuranceMoney.getId();
			String productType = product.getName();
			Date date = insuranceMoney.getApplyDate();
			String customerName = customer.getName();
			String status = insuranceMoney.getProcessStatus().getName();
			result.add(new GetAllInsuranceMoneyResponse(id, productType, date, customerName, status));
		}
		return result;
	}

	public List<InsuranceMoney> getAllUnprocessedInsuranceMoney() {
		return insuranceMoneyEntityModel.getAll().stream()
			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Unprocessed)
			.toList();

	}

	public List<InsuranceMoney> getAllProcessedInsuranceMoney() {
		return insuranceMoneyEntityModel.getAll().stream()
			.filter(e -> e.getProcessStatus() == InsuranceMoneyStatus.Completed)
			.toList();
	}

	public InsuranceMoney getInsuranceMoneyById(int id) throws NotExistException {
		return insuranceMoneyEntityModel.getById(id);
	}
	public Contract getContractById(int contractId) throws NotExistContractException, NotExistException {
		return contractEntityModel.getById(contractId);
	}

	public Customer getCustomerById(int id) throws NotExistException, NotExistContractException {
		return customerEntityModel.getById(id);
	}

	public List<GetAllReportResponse> getAllReport() {
		List<GetAllReportResponse> result = new ArrayList<GetAllReportResponse>();
		for (Report report : reportEntityModel.getAll()) {
			int id = report.getAccident().getId();
			String serviceType = report.getAccident().getServiceType().getName();
			String date = report.getAccident().getDate();
			String location = report.getAccident().getLocation();
			String customerName = report.getAccident().getCustomerName();
			String customerPhoneNumber = report.getAccident().getCustomerPhoneNumber();
			String accidentProcessStatus = report.getAccident().getProcessStatus().getName();
			String processStatus = report.getProcessStatus().getName();
			int damageAssessmentMoney = report.getDamageAssessmentMoney();
			result.add(new GetAllReportResponse(id, serviceType, date, location, customerName,
					customerPhoneNumber, accidentProcessStatus,
					processStatus, damageAssessmentMoney));
		}

		return result;
	}

	public Report getReportById(int id) throws NotExistException {
		return reportEntityModel.getById(id);
	}

	public List<Report> getAllUnprocessedReport() {
		return reportEntityModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == ReportProcessStatus.Unprocessed)
				.toList();
	}

	public List<Report> getAllCompletedReport() {
		return reportEntityModel.getAll().stream()
				.filter(e -> e.getProcessStatus() == ReportProcessStatus.Completed)
				.toList();
	}

	public Contract getAutomobileByCustomerId(int customerID) throws NotExistContractException, NotExistException {
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
	}
}
