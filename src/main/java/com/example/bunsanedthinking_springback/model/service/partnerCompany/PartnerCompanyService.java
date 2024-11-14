package com.example.bunsanedthinking_springback.model.service.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.partnerCompany.PartnerCompanyEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerCompanyService {
	@Autowired
	private PartnerCompanyEntityModel partnerCompanyEntityModel;
	@Autowired
	private ReportEntityModel reportEntityModel;

	public PartnerCompany getPartnerCompany(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyEntityModel.getById(id);
		if (partnerCompany == null) {
			throw new NotExistException();
		}
		return partnerCompany;
	}

	public List<Report> getAllReportByDamageAssessmentCompanyID(int id) throws NotExistException {
		if (partnerCompanyEntityModel.getById(id) == null) throw new NotExistException("해당 업체가 없습니다");
		return reportEntityModel.getAll().stream().filter(e -> e.getDamageAssessmentCompanyID() == id).toList();
	}

	public Report getReport(int id) throws NotExistException {
		Report report = reportEntityModel.getById(id);
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
//	} // 사용안되는 것 같아서 일단 막아둠

	public void setDamageAssessmentMoney(int accidentId, int damageAssessmentMoney) throws NotExistException {
		Report report = reportEntityModel.getById(accidentId);
		if (report == null) {
			throw new NotExistException("해당하는 신고 정보가 존재하지 않습니다.");
		}
		report.setDamageAssessmentMoney(damageAssessmentMoney);
		reportEntityModel.update(report);
	}
}
