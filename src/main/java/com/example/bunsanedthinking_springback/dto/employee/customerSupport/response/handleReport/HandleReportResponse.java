package com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.handleReport;

import java.text.ParseException;
import java.util.Date;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandleReportResponse {
	private String customerName;
	private String customerPhoneNumber;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;
	private int id;
	private String location;
	private String processStatus;
	private String serviceType;

	public static HandleReportResponse from(Accident accident) {
		try {
			return new HandleReportResponse(accident.getCustomerName(), accident.getCustomerPhoneNumber(),
				DateUtils.toDate(accident.getDate()), accident.getId(), accident.getLocation(),
				accident.getProcessStatus().getName(), accident.getServiceType().getName());
		} catch (ParseException e) {
			return null;
		}
	}
}
