package com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.handleReport;

import com.example.bunsanedthinking_springback.entity.accident.Accident;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandleReportResponse {
	private String customerName;
	private String customerPhoneNumber;
	private String date;
	private int id;
	private String location;
	private String processStatus;
	private String serviceType;

	public static HandleReportResponse from(Accident accident) {
		return new HandleReportResponse(accident.getCustomerName(), accident.getCustomerPhoneNumber(),
			accident.getDate(), accident.getId(), accident.getLocation(),
			accident.getProcessStatus().getName(), accident.getServiceType().getName());
	}
}
