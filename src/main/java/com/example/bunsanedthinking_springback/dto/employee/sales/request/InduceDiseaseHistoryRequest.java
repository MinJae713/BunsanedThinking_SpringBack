package com.example.bunsanedthinking_springback.dto.employee.sales.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InduceDiseaseHistoryRequest {
	private String dateOfDiagnosis;
	private String name;
}

