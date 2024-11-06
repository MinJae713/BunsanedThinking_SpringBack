package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryHistoryVO {
	private int id;
	private String hospital_name;
	private String name;
	private LocalDate date;
	private int customer_id;
}
