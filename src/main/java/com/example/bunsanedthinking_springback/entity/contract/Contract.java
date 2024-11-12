package com.example.bunsanedthinking_springback.entity.contract;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.ContractVO;
import lombok.AllArgsConstructor;
import lombok.Data;
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
@Data
public class Contract {

	public static final int CONTRACT_SERIAL_NUMBER = 100;

	private List<CompensationDetail> compensationDetailList;
	private ContractStatus contractStatus;
	private int customerID;
	private Date date;
	private List<DepositDetail> depositDetailList;
	private Integer employeeID;
	private Integer paymentDate; // 월 납부일
	private Date expirationDate;
	private int id;
	private List<InsuranceMoney> insuranceMoneyList;
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
		this.paymentDetailList = new ArrayList<>();
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
		LocalDate lDate = date == null ? null :
			new java.util.Date(date.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate lExpirationDate = expirationDate == null ? null :
			new java.util.Date(expirationDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate lTerminationDate = terminationDate == null ? null :
			new java.util.Date(terminationDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate lLastPaidDate = lastPaidDate == null ? null :
			new java.util.Date(lastPaidDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new ContractVO(id, lDate, lExpirationDate,
			paymentDate, lTerminationDate,
			contractStatus.ordinal(),
			customerID, employeeID,
			product.getId(), lLastPaidDate);
	}

	public Contract clone() {
		return new Contract(this);
	}

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.date);
	}

	public String getExpirationDate() {
		if (this.expirationDate == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.expirationDate);
	}

	public String getLastPaidDate() {
		if (this.lastPaidDate == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.lastPaidDate);
	}

	public String getTerminationDate() {
		if (this.terminationDate == null) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(this.terminationDate);
	}
}
