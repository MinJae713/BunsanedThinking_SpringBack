package com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.viewDepositDetail;

import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewDepositResponse {
	private String date;
	private String depositorName;
	private int id;
	private int money;
	private String path;

	public static ViewDepositResponse from(DepositDetail depositDetail) {
		return new ViewDepositResponse(depositDetail.getDate(), depositDetail.getDepositorName(),
			depositDetail.getId(), depositDetail.getMoney(), depositDetail.getPath().getText());
	}
}
