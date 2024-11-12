package com.example.bunsanedthinking_springback.entity.report;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.vo.ReportVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

	public static final int REPORT_SERIAL_NUMBER = 500;
	private Accident accident;
	private Integer damageAssessmentMoney;
	private int id;
	private int roadsideAssistanceCompanyID;
	private int damageAssessmentCompanyID;
	private ReportProcessStatus processStatus;

	public Report(Accident accident, PartnerCompany damageAssessmentCompany, PartnerCompany roadsideAssistanceCompany) {
		this.accident = accident;
		this.damageAssessmentMoney = -1;
		this.damageAssessmentCompanyID = damageAssessmentCompany.getId();
		this.roadsideAssistanceCompanyID = roadsideAssistanceCompany.getId();
		this.processStatus = ReportProcessStatus.Unprocessed;
	}

	public ReportVO findVO() {
		return new ReportVO(id, damageAssessmentMoney,
			processStatus.ordinal(),
			roadsideAssistanceCompanyID,
			damageAssessmentCompanyID);
	}

}
