package com.example.bunsanedthinking_springback.dto.sales;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SurgeryHistoryDTO {
	private String date;
	private String hospitalName;
	private String name;
}
