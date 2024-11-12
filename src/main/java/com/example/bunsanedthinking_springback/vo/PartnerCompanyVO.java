package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

	public PartnerCompany getEntity(List<Report> reports) {
		PartnerCompany partnerCompany = new PartnerCompany();
		partnerCompany.setId(id);
		partnerCompany.setHeadName(head_name);
		partnerCompany.setHeadPhoneNumber(head_phone_number);
		partnerCompany.setEvaluation(evaluation);
		partnerCompany.setName(name);
		partnerCompany.setPartnerCompanyType(PartnerCompanyType.values()[partner_company_type]);
		partnerCompany.setPhoneNumber(phone_number);
		partnerCompany.setReportList(reports);
		return partnerCompany;
	}
}
