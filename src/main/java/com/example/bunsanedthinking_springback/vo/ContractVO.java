package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDate;

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
}
