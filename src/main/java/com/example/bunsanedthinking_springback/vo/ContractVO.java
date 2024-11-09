package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.compensationDetail.CompensationDetail;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.entity.product.Product;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractVO {
	private int id;
	private LocalDate date;
	private LocalDate expiration_date;
	private Integer payment_date;
	private LocalDate termination_date;
	private int contract_status;
	private Integer customer_id;
	private Integer employee_id;
	private int product_id;
	private LocalDate lastpaid_date; // 추가

	public Contract getEntity(ArrayList<CompensationDetail> compensationDetails,
							  ArrayList<DepositDetail> depositDetails,
							  ArrayList<PaymentDetail> paymentDetails,
							  ArrayList<InsuranceMoney> insuranceMonies,
							  Product product) {
		Contract contract = new Contract();
		contract.setId(id);
		contract.setContractStatus(ContractStatus.values()[contract_status]);
		contract.setCustomerID(customer_id);
		contract.setDate(Date.valueOf(date));
		contract.setEmployeeID(employee_id);
		contract.setPaymentDate(payment_date);
		contract.setExpirationDate(Date.valueOf(expiration_date));
		contract.setLastPaidDate(Date.valueOf(lastpaid_date));
		contract.setTerminationDate(Date.valueOf(termination_date));

		contract.setCompensationDetailList(compensationDetails);
		contract.setDepositDetailList(depositDetails);
		contract.setInsuranceMoneyList(insuranceMonies);
		contract.setProduct(product);
		contract.setPaymentDetailList(paymentDetails);
		return contract;
	}
	
	public static ContractVO from(Contract contract) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date =
			contract.getDate() == null ? null : LocalDate.parse(contract.getDate(), formatter);
		LocalDate expirationDate =
			contract.getExpirationDate() == null ? null : LocalDate.parse(contract.getExpirationDate(), formatter);
		LocalDate terminationDate =
			contract.getTerminationDate() == null ? null : LocalDate.parse(contract.getTerminationDate(), formatter);
		LocalDate lastPaidDate =
			contract.getLastPaidDate() == null ? null : LocalDate.parse(contract.getLastPaidDate(), formatter);
		return new ContractVO(contract.getId(), date, expirationDate,
			contract.getPaymentDate(), terminationDate, contract.getContractStatus().ordinal(),
			contract.getCustomerID(), contract.getEmployeeID(), contract.getProduct().getId(),
			lastPaidDate);
	}
}
