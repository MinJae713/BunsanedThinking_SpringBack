package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.bunsanedthinking_springback.entity.contract.Contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
