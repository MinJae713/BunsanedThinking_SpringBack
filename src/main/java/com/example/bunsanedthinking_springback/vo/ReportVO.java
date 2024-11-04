package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportVO {
	private int accident_id;
	private Integer damage_assessment_money;
	private int process_status;
	private int roadside_assistance_company_id;
	private int damage_assessment_company_id;
}
