package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerCompanyVO {
	private int id;
	private String head_name;
	private String head_phone_number;
	private int evaluation;
	private String name;
	private int partner_company_type;
	private String phone_number;
}
