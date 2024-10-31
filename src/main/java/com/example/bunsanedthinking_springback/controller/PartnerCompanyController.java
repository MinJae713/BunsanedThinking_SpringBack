package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.partnerCompany.PartnerCompanyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/partnerCompany")
public class PartnerCompanyController {
	@Autowired
	private PartnerCompanyModel partnerCompanyModel;

	public PartnerCompany get(PartnerCompanyList partnerCompanyList, int id) throws NotExistException {
		return partnerCompanyModel.get(partnerCompanyList, id);
	}
	public ArrayList<Report> getAllReportByDamageAssessmentCompanyID(ReportList reportList, int id) {
		return partnerCompanyModel.getAllReportByDamageAssessmentCompanyID(reportList, id);
	}
	public Report get(ReportList reportList, int id) throws NotExistException {
		return partnerCompanyModel.get(reportList, id);
	}
	public void update(ReportList reportList, Report report) throws NotExistException {
		partnerCompanyModel.update(reportList, report);
	}
}
