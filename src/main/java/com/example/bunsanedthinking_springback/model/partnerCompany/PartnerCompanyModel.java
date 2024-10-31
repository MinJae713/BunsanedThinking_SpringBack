package com.example.bunsanedthinking_springback.model.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PartnerCompanyModel {
	public PartnerCompany get(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		return partnerCompanyList.get(id);
	}
	public ArrayList<Report> getAllReportByDamageAssessmentCompanyID(ReportList reportList, int id) {
		return reportList.getAllReportByDamageAssessmentCompanyID(id);
	}
	public Report get(ReportList reportList, int id) throws NotExistException {
		return reportList.get(id);
	}
	public void update(ReportList reportList, Report report) throws NotExistException {
		reportList.update(report);
	}
}
