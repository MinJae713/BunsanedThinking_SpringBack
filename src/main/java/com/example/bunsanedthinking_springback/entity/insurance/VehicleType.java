package com.example.bunsanedthinking_springback.entity.insurance;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
public enum VehicleType {
//	1. 소형 2. 중형 3. 대형
	
	Small("소형"),
	Medium("중형"),
	Large("대형");
	
	private String name;
	
	VehicleType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}