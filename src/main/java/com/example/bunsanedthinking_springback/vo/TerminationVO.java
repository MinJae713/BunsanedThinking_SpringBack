package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TerminationVO {
	private int contract_id;
	private LocalDateTime apply_date;
	private int termination_fee;
}
