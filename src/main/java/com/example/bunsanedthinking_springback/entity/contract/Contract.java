package com.example.bunsanedthinking_springback.entity.contract;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ȯ
 * @version 1.0
 * @created 27-5-2024 4:40:40
 */
@NoArgsConstructor
@AllArgsConstructor
public class Contract {

	public static final int CONTRACT_SERIAL_NUMBER = 100;

	private ArrayList<CompensationDetail> compensationDetailList;
	private ContractStatus contractStatus;
	private int customerID;
	private Date date;
	private ArrayList<DepositDetail> depositDetailList;
	private int employeeID;
	private Integer paymentDate; // 월 납부일
	private Date expirationDate;
	private int id;
	private ArrayList<InsuranceMoney> insuranceMoneyList;
	private Date lastPaidDate; // 최근 납부일
	private Product product;
	private Date terminationDate; // 해지일
	private List<PaymentDetail> paymentDetailList;

	public Contract(int customerID, Product product) {
		this.compensationDetailList = new ArrayList<>();
		this.contractStatus = ContractStatus.ContractRequesting;
		this.customerID = customerID;
		this.date = new Date();
		this.depositDetailList = new ArrayList<>();
		this.employeeID = -1;
		this.expirationDate = null;
		this.terminationDate = null;
		this.lastPaidDate = null;
		this.insuranceMoneyList = new ArrayList<>();
		this.paymentDate = 10;
		this.product = product;
	}

	public Contract(Contract contract) {
		this.id = contract.getId();
		this.compensationDetailList = contract.getCompensationDetailList();
		this.contractStatus = contract.getContractStatus();
		this.customerID = contract.getCustomerID();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.date = dateFormat.parse(contract.getDate());
			if (contract.getExpirationDate() != null) {
				this.expirationDate = dateFormat.parse(contract.getExpirationDate());
			} else {
				this.expirationDate = null;
			}
			if (contract.getLastPaidDate() != null) {
				this.lastPaidDate = dateFormat.parse(contract.getLastPaidDate());
			} else {
				this.lastPaidDate = null;
			}
			if (contract.getTerminationDate() != null) {
				this.terminationDate = dateFormat.parse(contract.getTerminationDate());
			} else {
				this.terminationDate = null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.paymentDate = contract.getPaymentDate();
		this.depositDetailList = contract.getDepositDetailList();
		this.employeeID = contract.getEmployeeID();
		this.insuranceMoneyList = contract.getInsuranceMoneyList();
		this.product = contract.getProduct();
		this.paymentDetailList = contract.getPaymentDetailList();
	}

	public Contract(ArrayList<InsuranceMoney> insuranceMonies,
		ArrayList<CompensationDetail> compensationDetails,
		ArrayList<DepositDetail> depositDetails,
		ArrayList<PaymentDetail> paymentDetails,
		Product product, ContractVO contractVO) {
		setInsuranceMoneyList(insuranceMonies);
		setCompensationDetailList(compensationDetails);
		setDepositDetailList(depositDetails);
		setPaymentDetailList(paymentDetails);
		setProduct(product);

		setId(contractVO.getId());
		setDate(java.sql.Date.valueOf(contractVO.getDate()));
		setExpirationDate(java.sql.Date.valueOf(contractVO.getExpiration_date()));
		setPaymentDate(contractVO.getPayment_date());

		LocalDate terminationDate = contractVO.getTermination_date();
		if (terminationDate != null)
			setTerminationDate(java.sql.Date.valueOf(terminationDate));
		setContractStatus(ContractStatus.values()[contractVO.getContract_status()]);
		setCustomerID(contractVO.getCustomer_id());
		setEmployeeID(contractVO.getEmployee_id());
		setLastPaidDate(java.sql.Date.valueOf(contractVO.getLastpaid_date()));
	}

	public ContractVO findVO() {
		LocalDate lDate = LocalDate.of(date.getYear(), date.getMonth()+1, date.getDay());
		LocalDate lExpirationDate = LocalDate.of(expirationDate.getYear(), expirationDate.getMonth()+1, expirationDate.getDay());
		LocalDate lTerminationDate = LocalDate.of(terminationDate.getYear(), terminationDate.getMonth()+1, terminationDate.getDay());
		LocalDate lLastPaidDate = LocalDate.of(lastPaidDate.getYear(), lastPaidDate.getMonth()+1, lastPaidDate.getDay());
		return new ContractVO(id, lDate, lExpirationDate,
				paymentDate, lTerminationDate,
				contractStatus.ordinal(),
				customerID, employeeID,
				product.getId(), lLastPaidDate);
	}

	public Contract clone() {
		return new Contract(this);
	}

	public ArrayList<CompensationDetail> getCompensationDetailList() {
		return compensationDetailList;
	}

	public void setCompensationDetailList(ArrayList<CompensationDetail> compensationDetailList) {
		this.compensationDetailList = compensationDetailList;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<DepositDetail> getDepositDetailList() {
		return depositDetailList;
	}

	public void setDepositDetailList(ArrayList<DepositDetail> depositDetailList) {
		this.depositDetailList = depositDetailList;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getExpirationDate() {
		if (this.expirationDate == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.expirationDate);
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ArrayList<InsuranceMoney> getInsuranceMoneyList() {
		return insuranceMoneyList;
	}

	public void setInsuranceMoneyList(ArrayList<InsuranceMoney> insuranceMoneyList) {
		this.insuranceMoneyList = insuranceMoneyList;
	}

	public int getPaymentDate() {
		return this.paymentDate;
	}

	public void setPaymentDate(Integer paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public void review(boolean result, ContractList contractList) throws NotExistContractException {
		if (result) {
			LocalDate currentDate = LocalDate.now();
			if (this.product instanceof Insurance) {
				LocalDate expirationDate = currentDate.plusYears(((Insurance)this.product).getContractPeriod());
				this.expirationDate = Date.from(expirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			}
			this.date = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			this.contractStatus = ContractStatus.Maintaining;
			contractList.update(this);
		} else {
			this.contractStatus = ContractStatus.Terminating;
			contractList.update(this);
		}
	}

	public String getLastPaidDate() {
		if (this.lastPaidDate == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.lastPaidDate);
	}

	public void setLastPaidDate(Date lastPaidDate) {
		this.lastPaidDate = lastPaidDate;
	}

	public String getTerminationDate() {
		if (this.terminationDate == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.terminationDate);
	}

	public void setTerminationDate(Date terminationDate) {
		this.terminationDate = terminationDate;
	}

	public void addCompensationDetail(CompensationDetail compensationDetail) {
		this.compensationDetailList.add(compensationDetail);
	}

	public List<PaymentDetail> getPaymentDetailList() {
		return paymentDetailList;
	}

	public void setPaymentDetailList(List<PaymentDetail> paymentDetailList) {
		this.paymentDetailList = paymentDetailList;
	}

	public double getOutcome() {
		if (this.getProduct() instanceof Loan) {
			ArrayList<CompensationDetail> compensationDetailList = this.compensationDetailList;
			CompensationDetail compensationDetail = compensationDetailList.get(compensationDetailList.size());
			return compensationDetail.getMoney() * ((Loan)this.getProduct()).getInterestRate() / 100;
		} else if (this.getProduct() instanceof Insurance) {
			return ((Insurance)this.getProduct()).getMonthlyPremium();
		} else {
			return 0;
		}
	}
}
