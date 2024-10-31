package com.example.bunsanedthinking_springback.entity.partnerCompany;


/**
 * @author KimChan
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
public enum PartnerCompanyType {
	
	Hospital("병원"),
	RepairShop("카센터"),
	LawFirm("법무법인"),
	DamageAssessmentCompany("손해사정업체"),
	RoadsideAssistanceCompany("긴급출동회사");
	
	private String name;

	PartnerCompanyType(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
}