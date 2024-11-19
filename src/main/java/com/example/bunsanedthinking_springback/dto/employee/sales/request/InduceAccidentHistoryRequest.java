package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceAccidentHistoryRequest {
	private String date;
	private String accidentDetail;
}
