package com.example.bunsanedthinking_springback.model.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyList;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportList;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.PartnerCompanyMapper;
import com.example.bunsanedthinking_springback.repository.ReportMapper;
import com.example.bunsanedthinking_springback.vo.ReportVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerCompanyModel {
	@Autowired
	private PartnerCompanyMapper partnerCompanyMapper;
	@Autowired
	private ReportMapper reportMapper;

	public PartnerCompany getPartnerCompany(int id) throws NotExistException {
		PartnerCompany partnerCompany = partnerCompanyMapper.findById_PartnerCompany(id);
		if (partnerCompany == null) {
			throw new NotExistException();
		}
		return partnerCompany;
	}
	public ArrayList<Report> getAllReportByDamageAssessmentCompanyID(int id) {
		List<Report> reports = reportMapper.findAllByDamageAssessmentCompanyID_PartnerCompany(id);
		return new ArrayList<>(reports);
	}
	public Report getReport(int id) throws NotExistException {
		Report report = reportMapper.findById_PartnerCompany(id);
		if (report == null) {
			throw new NotExistException();
		}
		return report;
	}
	public void update(Report report) throws NotExistException {
		Report existingReport = reportMapper.findById_PartnerCompany(report.getId());
		if (existingReport == null) {
			throw new NotExistException();
		}
		reportMapper.update_PartnerCompany(report);
	}

	public void setDamageAssessmentMoney(int reportId, int damageAssessmentMoney) throws NotExistException {
		ReportVO reportVO = reportMapper.getById_Compensation(reportId)
			.orElseThrow(() -> new NotExistException("해당하는 신고 정보가 존재하지 않습니다."));
		reportVO.setDamage_assessment_money(damageAssessmentMoney);
		reportMapper.update(reportVO);
	}
}
