package com.example.bunsanedthinking_springback.entity.partnerCompany;

import lombok.Getter;

@Getter
public enum PartnerCompanyType {
	
//	Hospital("병원"),
//	RepairShop("카센터"),
//	LawFirm("법무법인"),
	// 협력업체의 레포트 받아오는 로직이
	// 협력업체 더미의 타입이랑 안맞아서 일단 주석으로 막아놨슴다
	// 협력업체 타입이 1. 긴급출동, 2. 손해사정이 되게끔 하다보니 0은 더미로 넣어놨어유
	DumyCompany("더미"),
	RoadsideAssistanceCompany("긴급출동회사"),
	DamageAssessmentCompany("손해사정업체");

	private String name;

	PartnerCompanyType(String name) {
		this.name = name;
	}

	public static PartnerCompanyType indexOf(int index) {
		for (PartnerCompanyType type : PartnerCompanyType.values()) {
			if (type.ordinal() == index) {
				return type;
			}
		}
		throw new IllegalArgumentException("잘못된 Partner Company Type이 입력되었습니다.");
	}
	
}
