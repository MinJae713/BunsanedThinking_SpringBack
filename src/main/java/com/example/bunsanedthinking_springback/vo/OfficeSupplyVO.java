package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeSupplyVO {
	private int id;
	private int inventory;
	private String name;
	private int total_inventory;
	private String description;
	private int department_id;
}
