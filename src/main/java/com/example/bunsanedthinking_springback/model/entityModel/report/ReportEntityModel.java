package com.example.bunsanedthinking_springback.model.entityModel.report;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.accident.AccidentEntityModel;
import com.example.bunsanedthinking_springback.repository.ReportMapper;
import com.example.bunsanedthinking_springback.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportEntityModel {
	@Autowired
	private ReportMapper reportMapper;
	@Autowired
	private AccidentEntityModel accidentEntityModel;
	@ReadOnly
	public Report getById(int id) {
		Accident accident = accidentEntityModel.getById(id);
		if (accident == null)
			return null;
		return reportMapper.getById(id)
			.map(reportVO -> reportVO.getEntity(accident))
			.orElse(null);
	}
	@ReadOnly
	public List<Report> getAll() {
		List<Report> reports = new ArrayList<Report>();
		for (ReportVO reportVO : reportMapper.getAll()) {
			Accident accident = accidentEntityModel.getById(reportVO.getAccident_id());
			reports.add(reportVO.getEntity(accident));
		}
		return reports;
	}
	@ReadOnly
	public Integer getMaxId() {
		return reportMapper.getMaxId();
	}

	public void add(Report report) {
		if (report == null) return;
		if (reportMapper.getById(report.getId()).isPresent()) return;
		reportMapper.insert(report.findVO());
	}

	public void update(Report report) {
		if (report == null) return;
		if (reportMapper.getById(report.getId()).isEmpty()) return;
		accidentEntityModel.update(report.getAccident());
		reportMapper.update(report.findVO());
	}

	public void delete(int id) {
		if (reportMapper.getById(id).isEmpty()) return;
		Report report = getById(id);
		reportMapper.deleteById(id);
		Accident accident = report.getAccident();
		if (accident == null) return;
		accidentEntityModel.delete(accident.getId());
	}
}
