package com.example.bunsanedthinking_springback.dto.ProductManagement;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DiseaseDTO {
	private Integer id;
	private String name;
	private Integer maximumMoney;
	private Integer insuranceType;
	private Integer monthlyPremium;
	private Integer contractPeriod;
	private String coverage;
	private String diseaseName;
	private Integer diseaseLimit;
	private Integer surgeriesLimit;
}
