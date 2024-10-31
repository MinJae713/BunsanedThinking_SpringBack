package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SurgeryHistoryVO {
	private int id;
	private String hospital_name;
	private String name;
	private LocalDateTime date;
	private int customer_id;
}
