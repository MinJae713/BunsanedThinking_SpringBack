package com.example.bunsanedthinking_springback.entity.partnerCompany;

import com.example.bunsanedthinking_springback.entity.report.Report;

import java.util.ArrayList;

import lombok.AllArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:43
 */
//2024-06-04 김대현
@AllArgsConstructor
public class PartnerCompany implements Cloneable {

	public static int PARTNER_COMPANY_SERIAL_NUMBER = 300;
	private int evaluation;
	private String headName;
	private String headPhoneNumber;
	private int id;
	private String name;
	private PartnerCompanyType partnerCompanyType;
	private String phoneNumber;
	private ArrayList<Report> reportList;

	public PartnerCompany(String name, String phoneNumber, PartnerCompanyType partnerCompanyType, String headName,String headPhoneNumber){
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setPartnerCompanyType(partnerCompanyType);
		this.setHeadName(headName);
		this.setHeadPhoneNumber(headPhoneNumber);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getHeadPhoneNumber() {
		return headPhoneNumber;
	}

	public void setHeadPhoneNumber(String headPhoneNumber) {
		this.headPhoneNumber = headPhoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PartnerCompanyType getPartnerCompanyType() {
		return partnerCompanyType;
	}

	public void setPartnerCompanyType(PartnerCompanyType partnerCompanyType) {
		this.partnerCompanyType = partnerCompanyType;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public ArrayList<Report> getReportList() {
		return reportList;
	}

	public void setReportList(ArrayList<Report> reportList) {
		this.reportList = reportList;
	}
	
	public PartnerCompany clone() {
		PartnerCompany partnerCompany = new PartnerCompany(getName(), getPhoneNumber(), getPartnerCompanyType(), getHeadName(), getHeadPhoneNumber());
		partnerCompany.setId(getId());
		partnerCompany.setEvaluation(getEvaluation());
		partnerCompany.setReportList(getReportList());
		return partnerCompany;
	}

}
