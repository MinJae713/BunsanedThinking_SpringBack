package com.example.bunsanedthinking_springback.vo;

import lombok.Data;

@Data
public class OfficeSupplyVO {
	private int id;
	private int inventory;
	private String name;
	private int total_inventory;
	private String description;
	private int department_id;
}
