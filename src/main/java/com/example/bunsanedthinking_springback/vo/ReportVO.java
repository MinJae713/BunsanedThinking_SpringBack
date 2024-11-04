package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.entity.report.ReportProcessStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportVO {
	private int accident_id;
	private String damage_assessment_detail;
	private int process_status;
	private int roadside_assistance_company_id;
	private int damage_assessment_company_id;

	public Report getReport(Accident accident) {
		Report result = new Report();
		result.setId(accident.getId()); // 이거가 맞나유
		result.setAccident(accident);
		result.setDamageAssessmentMoney(-713);
		result.setRoadsideAssistanceCompanyID(roadside_assistance_company_id);
		result.setDamageAssessmentCompanyID(damage_assessment_company_id);
		result.setProcessStatus(ReportProcessStatus.values()[process_status]);
		return result;
	}
}
