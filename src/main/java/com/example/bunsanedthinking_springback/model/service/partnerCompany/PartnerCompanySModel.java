package com.example.bunsanedthinking_springback.model.service.partnerCompany;

import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.PartnerCompanyMapper;
import com.example.bunsanedthinking_springback.repository.ReportMapper;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import com.example.bunsanedthinking_springback.vo.ReportVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PartnerCompanySModel {
	@Autowired
	private PartnerCompanyMapper partnerCompanyMapper;
	@Autowired
	private ReportMapper reportMapper;

	public PartnerCompanyVO getPartnerCompany(int id) throws NotExistException {
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_PartnerCompany(id);
		if (partnerCompanyVO == null) {
			throw new NotExistException();
		}
		return partnerCompanyVO;
	}

	public ArrayList<ReportVO> getAllReportByDamageAssessmentCompanyID(int id) {
		List<ReportVO> reports = reportMapper.findAllByDamageAssessmentCompanyID_PartnerCompany(id);
		return new ArrayList<>(reports);
	}

	public ReportVO getReport(int id) throws NotExistException {
		ReportVO reportVO = reportMapper.findById_PartnerCompany(id);
		if (reportVO == null) {
			throw new NotExistException();
		}
		return reportVO;
	}

	public void update(ReportVO reportVO) throws NotExistException {
		Optional<ReportVO> existingReportVO = reportMapper.getById_Compensation(reportVO.getAccident_id());
		if (existingReportVO.isEmpty()) {
			throw new NotExistException();
		}
		reportMapper.updateReport_PartnerCompany(reportVO);
	}

	public void setDamageAssessmentMoney(int reportId, int damageAssessmentMoney) throws NotExistException {
		ReportVO reportVO = reportMapper.getById_Compensation(reportId)
			.orElseThrow(() -> new NotExistException("해당하는 신고 정보가 존재하지 않습니다."));
		reportVO.setDamage_assessment_money(damageAssessmentMoney);
		reportMapper.update(reportVO);
	}
}
