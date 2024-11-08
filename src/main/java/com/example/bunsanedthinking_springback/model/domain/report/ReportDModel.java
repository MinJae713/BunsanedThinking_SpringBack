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
//		accidentDModel.add(report.getAccident());
		// 위는 신고 내역 추가 시 사고 내역이 기존에 없어서 새로 추가한다는 가정
		// 여기서 신고에 대한 사고 내역이 이미 있는 상황이라면??
		// 기존에 추가된 사고 내역이 있는지 먼저 보고 사고 내역을 추가해야 함
		if (accidentDModel.getById(report.getAccident().getId()) == null)
			accidentDModel.add(report.getAccident());
		reportMapper.insert_CustomerSupport(report.getVO());
	}

	public void update(Report report) {
		reportMapper.update(report.getVO());
		accidentDModel.update(report.getAccident());
		// 이건 사고 정보가 수정될 수도 있고 없을 수도 있어서 그대로 반영되도록 함
	}

	public void delete(int id) {
		// 이건 신고 정보 삭제 시 사고 정보도 같이 지워야되나??
		// ㄴ 신고 정보를 삭제한다고 사고 정보도 같이 삭제한다는 전제는 없는거 같음
		if (getById(id) == null) return;
		reportMapper.deleteById(id);
	}
}
