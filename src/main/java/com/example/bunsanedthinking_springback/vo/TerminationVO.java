package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TerminationVO {
	private int contract_id;
	private LocalDateTime apply_date;
	private int termination_fee;
}
