package com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.handlePaymentDetail;

import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandlePaymentResponse {
	private int id;
	private String processStatus;
	private String accountHolder;
	private String bank;
	private String bankAccount;
	private int money;
	private String paymentType;

	public static HandlePaymentResponse from(PaymentDetail paymentDetail) {
		return new HandlePaymentResponse(paymentDetail.getId(), paymentDetail.getProcessStatus().getName(),
			paymentDetail.getAccountHolder(), paymentDetail.getBank(), paymentDetail.getBankAccount(),
			paymentDetail.getMoney(), paymentDetail.getPaymentType().getName());
	}
}
