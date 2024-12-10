package com.example.bunsanedthinking_springback.model.entityModel.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompanyType;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.report.ReportEntityModel;
import com.example.bunsanedthinking_springback.repository.PartnerCompanyMapper;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerCompanyEntityModel {
	@Autowired
	private PartnerCompanyMapper partnerCompanyMapper;
	@Autowired
	private ReportEntityModel reportEntityModel;
	@ReadOnly
	public PartnerCompany getById(int id) {
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.getById(id).orElse(null);
		if (partnerCompanyVO == null) return null;
		List<Report> reports = new ArrayList<Report>();
		if (PartnerCompanyType.values()[partnerCompanyVO.getPartner_company_type()] ==
				PartnerCompanyType.RoadsideAssistanceCompany) {
			reports = reportEntityModel.getAll().stream().
					filter(e -> e.getRoadsideAssistanceCompanyID() == id).toList();
		}
		else if (PartnerCompanyType.values()[partnerCompanyVO.getPartner_company_type()] ==
				PartnerCompanyType.DamageAssessmentCompany) {
			reports = reportEntityModel.getAll().stream().
					filter(e -> e.getDamageAssessmentCompanyID() == id).toList();
		}
		return partnerCompanyVO.getEntity(reports);
	}
	@ReadOnly
	public List<PartnerCompany> getAll() {
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		partnerCompanyMapper.getAll()
				.forEach(e -> partnerCompanies.add(getById(e.getId())));
		return partnerCompanies;
	}
	@ReadOnly
	public List<PartnerCompany> getAll_RoadsideCompany() {
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		List<PartnerCompanyVO> partnerCompanyVOS = partnerCompanyMapper.getAll().
				stream().filter(e -> e.getPartner_company_type() == 4).toList();

		for (PartnerCompanyVO partnerCompanyVO : partnerCompanyVOS)
			partnerCompanies.add(partnerCompanyVO.getEntity(reportEntityModel.getAll().stream().
					filter(e -> e.getRoadsideAssistanceCompanyID() == partnerCompanyVO.getId()).toList()));
		return partnerCompanies;
	}
	@ReadOnly
	public List<PartnerCompany> getAll_DamageAssesmentCompany() {
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		List<PartnerCompanyVO> partnerCompanyVOS = partnerCompanyMapper.getAll().
				stream().filter(e -> e.getPartner_company_type() == 3).toList();
		for (PartnerCompanyVO partnerCompanyVO : partnerCompanyVOS)
			partnerCompanies.add(partnerCompanyVO.getEntity(reportEntityModel.getAll().stream().
					filter(e -> e.getDamageAssessmentCompanyID() == partnerCompanyVO.getId()).toList()));
		return partnerCompanies;
	}
	@ReadOnly
	public Integer getMaxId() {
		return partnerCompanyMapper.getMaxId();
	}

	public void add(PartnerCompany partnerCompany) {
		if (partnerCompany == null) return;
		if (partnerCompanyMapper.getById(partnerCompany.getId()).isPresent()) return;
		partnerCompanyMapper.insert(partnerCompany.findVO());

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportEntityModel.add(e));
	}

	public void update(PartnerCompany partnerCompany) {
		if (partnerCompany == null) return;
		if (partnerCompanyMapper.getById(partnerCompany.getId()).isEmpty()) return;

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportEntityModel.update(e));

		partnerCompanyMapper.update(partnerCompany.findVO());
	}

	public void delete(int id) {
		if (partnerCompanyMapper.getById(id).isEmpty()) return;
		PartnerCompany partnerCompany = getById(id);

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportEntityModel.delete(id));

		partnerCompanyMapper.deleteById(id);
	}
}
