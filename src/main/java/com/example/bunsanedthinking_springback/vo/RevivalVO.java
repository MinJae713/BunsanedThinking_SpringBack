package com.example.bunsanedthinking_springback.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevivalVO {
	private int id;
	private LocalDateTime apply_date;
}