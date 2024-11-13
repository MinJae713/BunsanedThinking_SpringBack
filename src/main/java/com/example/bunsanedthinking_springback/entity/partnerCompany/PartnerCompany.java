package com.example.bunsanedthinking_springback.entity.partnerCompany;

import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerCompany implements Cloneable {
	private int evaluation;
	private String headName;
	private String headPhoneNumber;
	private int id;
	private String name;
	private PartnerCompanyType partnerCompanyType;
	private String phoneNumber;
	private List<Report> reportList;

	public PartnerCompany(String name, String phoneNumber, PartnerCompanyType partnerCompanyType, String headName,String headPhoneNumber){
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setPartnerCompanyType(partnerCompanyType);
		this.setHeadName(headName);
		this.setHeadPhoneNumber(headPhoneNumber);
	}
	public PartnerCompanyVO findVO() {
		return new PartnerCompanyVO(id, headName,
				headPhoneNumber, evaluation,
				name, partnerCompanyType.ordinal(),
				phoneNumber);
	}
	
	public PartnerCompany clone() {
		PartnerCompany partnerCompany = new PartnerCompany(getName(), getPhoneNumber(), getPartnerCompanyType(), getHeadName(), getHeadPhoneNumber());
		partnerCompany.setId(getId());
		partnerCompany.setEvaluation(getEvaluation());
		partnerCompany.setReportList(getReportList());
		return partnerCompany;
	}

}
