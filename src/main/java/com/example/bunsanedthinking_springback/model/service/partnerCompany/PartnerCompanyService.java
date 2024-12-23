package com.example.bunsanedthinking_springback.model.service.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.constants.service.partnerCompany.PartnerCompanyConstants;
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

	public PartnerCompany getPartnerCompanyById(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyEntityModel.getById(id);
		if (partnerCompany == null) {
			throw new NotExistException();
		}
		return partnerCompany;
	}

	public List<Report> getAllReportByDamageAssessmentCompanyID(int id) throws NotExistException {
		if (partnerCompanyEntityModel.getById(id) == null) throw new NotExistException(PartnerCompanyConstants.PARTNER_COMPANY_NULL);
		return reportEntityModel.getAll().stream().filter(e -> e.getDamageAssessmentCompanyID() == id).toList();
	}

	public Report getReport(int id) throws NotExistException {
		Report report = reportEntityModel.getById(id);
		if (report == null) {
			throw new NotExistException();
		}
		return report;
	}

	public void setDamageAssessmentMoney(int accidentId, int damageAssessmentMoney) throws NotExistException {
		if (damageAssessmentMoney < 0) {
			throw new IllegalArgumentException(PartnerCompanyConstants.DAMAGE_ASSESSMENT_MONEY_NEGATIVE_MESSAGE);
		}
		Report report = reportEntityModel.getById(accidentId);
		if (report == null) {
			throw new NotExistException(PartnerCompanyConstants.REPORT_NOT_FOUND);
		}
		report.setDamageAssessmentMoney(damageAssessmentMoney);
		reportEntityModel.update(report);
	}
}
