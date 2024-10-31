package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryHistoryVO {
	private int id;
	private String hospital_name;
	private String name;
	private LocalDateTime date;
	private int customer_id;
}
