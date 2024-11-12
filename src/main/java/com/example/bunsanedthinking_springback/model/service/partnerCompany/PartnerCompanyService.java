package com.example.bunsanedthinking_springback.model.service.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.partnerCompany.PartnerCompanyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PartnerCompanyService {
	@Autowired
	private PartnerCompanyEntityModel partnerCompanyDModel;
	@Autowired
	private ReportEntityModel reportDModel;
//	@Autowired
//	private PartnerCompanyMapper partnerCompanyMapper;
//	@Autowired
//	private ReportMapper reportMapper;

	public PartnerCompany getPartnerCompany(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyDModel.getById(id);
		if (partnerCompany == null) {
			throw new NotExistException();
		}
		return partnerCompany;
	}

	public ArrayList<Report> getAllReportByDamageAssessmentCompanyID(int id) {
		ArrayList<Report> reports = reportDModel.getAllByDamageAssessmentCId(id);
		return reports;
	}

	public Report getReport(int id) throws NotExistException {
		Report report = reportDModel.getById(id);
		if (report == null) {
			throw new NotExistException();
		}
		return report;
	}

//	public void update(UpdateReportDTO updateReportDTO) throws NotExistException {
//		Report report = reportDModel.getById(updateReportDTO.getAccident_id());
//		if (report == null) {
//			throw new NotExistException();
//		}
//		report.setDamageAssessmentMoney(updateReportDTO.getDamage_assessment_money());
//		reportDModel.update(report);
//	} //모르겠음

	public void setDamageAssessmentMoney(int accidentId, int damageAssessmentMoney) throws NotExistException {
		Report report = reportDModel.getById(accidentId);
		if (report == null) {
			throw new NotExistException("해당하는 신고 정보가 존재하지 않습니다.");
		}
		report.setDamageAssessmentMoney(damageAssessmentMoney);
		reportDModel.update(report);
	}
}
