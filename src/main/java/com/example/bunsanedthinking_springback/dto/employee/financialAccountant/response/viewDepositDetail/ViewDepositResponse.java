package com.example.bunsanedthinking_springback.dto.employee.financialAccountant.response.viewDepositDetail;

import java.text.ParseException;
import java.util.Date;

import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ViewDepositResponse {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	private String depositorName;
	private int id;
	private int money;
	private String path;

	public static ViewDepositResponse from(DepositDetail depositDetail) {
		try {
			return new ViewDepositResponse(DateUtils.toDate(depositDetail.getDate()), depositDetail.getDepositorName(),
				depositDetail.getId(), depositDetail.getMoney(), depositDetail.getPath().getText());
		} catch (ParseException e) {
			return null;
		}
	}
}
