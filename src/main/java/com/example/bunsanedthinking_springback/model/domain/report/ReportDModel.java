package com.example.bunsanedthinking_springback.model.domain.report;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.model.domain.accident.AccidentDModel;
import com.example.bunsanedthinking_springback.repository.ReportMapper;
import com.example.bunsanedthinking_springback.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportDModel {
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private AccidentDModel accidentDModel;

	public Report getById(int id) {
		Accident accident = accidentDModel.getById(id);
		if (accident == null)
			return null;
		return reportMapper.getById_Compensation(id)
			.map(reportVO -> reportVO.getEntity(accident))
			.orElse(null);
	}

	public ArrayList<Report> getAllByRoadSideAssistanceCId(int id) {
		ArrayList<Report> reports = new ArrayList<>();
		List<ReportVO> reportVOS = reportMapper.getAllByRoadSideAssistanceCId(id);
		for (ReportVO reportVO : reportVOS) {
			Accident accident = accidentDModel.getById(reportVO.getAccident_id());
			reports.add(reportVO.getEntity(accident));
		}
		return reports;
	}

	public ArrayList<Report> getAllByDamageAssessmentCId(int id) {
		ArrayList<Report> reports = new ArrayList<>();
		List<ReportVO> reportVOS = reportMapper.findAllByDamageAssessmentCompanyID_PartnerCompany(id);
		for (ReportVO reportVO : reportVOS) {
			Accident accident = accidentDModel.getById(reportVO.getAccident_id());
			reports.add(reportVO.getEntity(accident));
		}
		return reports;
	}

	public List<Report> getAll() {
		List<Report> reports = new ArrayList<Report>();
		for (ReportVO reportVO : reportMapper.getAll_Compensation()) {
			Accident accident = accidentDModel.getById(reportVO.getAccident_id());
			reports.add(reportVO.getEntity(accident));
		}
		return reports;
	}

	public Integer getMaxId() {
		return reportMapper.getMaxId();
	}

	public void add(Report report) {
		if (report == null) return;
		if (reportMapper.getById_Compensation(report.getId()).isPresent()) return;
		accidentDModel.add(report.getAccident());
		reportMapper.insert_CustomerSupport(report.findVO());
	}

	public void update(Report report) {
		if (report == null) return;
		if (reportMapper.getById_Compensation(report.getId()).isEmpty()) return;
		accidentDModel.update(report.getAccident());
		reportMapper.update(report.findVO());
		// 이건 사고 정보가 수정될 수도 있고 없을 수도 있어서 그대로 반영되도록 함
	}

	public void delete(int id) {
		if (reportMapper.getById_Compensation(id).isEmpty()) return;
		Report report = getById(id);
		reportMapper.deleteById(id);
		Accident accident = report.getAccident();
		if (accident == null) return;
		accidentDModel.delete(accident.getId());
	}
}
