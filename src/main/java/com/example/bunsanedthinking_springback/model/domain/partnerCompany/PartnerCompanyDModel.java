package com.example.bunsanedthinking_springback.model.domain.partnerCompany;

import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.entity.report.Report;
import com.example.bunsanedthinking_springback.model.domain.report.ReportDModel;
import com.example.bunsanedthinking_springback.repository.PartnerCompanyMapper;
import com.example.bunsanedthinking_springback.vo.PartnerCompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerCompanyDModel {
	@Autowired
	private PartnerCompanyMapper partnerCompanyMapper;
	@Autowired
	private ReportDModel reportDModel;

	public PartnerCompany getById_RoadsideCompany(int id) {
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_PartnerCompany(id);
		ArrayList<Report> reports = reportDModel.getAllByRoadSideAssistanceCId(id);
		return partnerCompanyVO.getEntity(reports);
	}

	public PartnerCompany getById_DamageAssesmentCompany(int id) {
		PartnerCompanyVO partnerCompanyVO = partnerCompanyMapper.findById_PartnerCompany(id);
		ArrayList<Report> reports = reportDModel.getAllByDamageAssessmentCId(id);
		return partnerCompanyVO.getEntity(reports);
	}
	// getById, getAll은 종류가 둘로 나뉨
	// 협력업체 안의 Report 리스트가 roadside 기준 목록이냐, damage 기준 목록이냐로 둘로 나눴음
	// roadside면 id == roadsideCompanyId, damage면 id == damageAssesmentCompanyId로 봄
	// 솔직히.... entity나 테이블구조가 잘못 되어있는거 갖기도...
	// ㄴ 그냥 더미가 잘못들어가있는건가... roadside가 1~4, damage가 5~8이면
	//    Report 테이블 companyId 열도 그에 맞춰 들어가야 하는데
	//    지금 더미는 roadside가 1~4, damage도 1~4 이렇게 드가있어유
	// ㄴ 요약: PartnerCompanyType을 damage로 지정해주면 id도 그에 맞춰(5~8)로 드가야되는데,
	//    더미 들어간거 보면 id가 roadside 업체인 상태

	// 저라면 roadside 업체 테이블이랑 damageAssesment를 나눴을거 같기도 합니다
	// ㄴ 반정규화처럼 해서  PartnerCompany 테이블 서브타입 2개 추가(RoadSide, DamageAssesment)
	// ㄴ 지금 Report가 companyId 갖고 있는걸 각각 RoadSide, DamageAssesment 테이블 참조하도록 변경
	// ㄴ *** RoadSide, DamageAssesment 여기엔 아이디밖에 안들어가는데, 대신 데이터 불일치는 방지할 수 있지 않을까
	//    ㄴ PK만 있는 테이블을 따로 만들어두면 그 테이블 참조로 인해 Report에 드가는 companyId 값을 모호성 없이 정할 수 있기 때문
	//    ㄴ 어찌보면 엔티티엔 없지만 테이블에 있는 Service테이블 같은 느낌...? 엔티티는 지금처럼 company_type만 지정해두고 테이블만 분리하는 방식
	// ㄴ 근디 하나 고려할 거 - 이 경우라면 PartnerCompany 테이블 insert나 delete 할 때
	//    partnerCompany 말고도 RoadSide랑 Damage쪽도 insert, delete된다는거

	// DModel 만들것이 많아서 그냥 여기에 남겨둘테니 풀 받아서 이거 보시는 분은 저에게 말씀해주십슈
	public List<PartnerCompany> getAll_RoadsideCompany() {
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		for (PartnerCompanyVO partnerCompanyVO : partnerCompanyMapper.getAll_CompensationPlanning()) {
			ArrayList<Report> reports = reportDModel.getAllByRoadSideAssistanceCId(partnerCompanyVO.getId());
			partnerCompanies.add(partnerCompanyVO.getEntity(reports));
		}
		return partnerCompanies;
	}

	public List<PartnerCompany> getAll_DamageAssesmentCompany() {
		List<PartnerCompany> partnerCompanies = new ArrayList<PartnerCompany>();
		for (PartnerCompanyVO partnerCompanyVO : partnerCompanyMapper.getAll_CompensationPlanning()) {
			ArrayList<Report> reports = reportDModel.getAllByDamageAssessmentCId(partnerCompanyVO.getId());
			partnerCompanies.add(partnerCompanyVO.getEntity(reports));
		}
		return partnerCompanies;
	}

	public Integer getMaxId() {
		return partnerCompanyMapper.getMaxId_CompensationPlanning();
	}

	public void add(PartnerCompany partnerCompany) {
		if (partnerCompany == null) return;
		if (partnerCompanyMapper.findById_CustomerSupport(partnerCompany.getId()).isPresent()) return;
		partnerCompanyMapper.insert_CompensationPlanning(partnerCompany.findVO());

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportDModel.add(e));
	}

	public void update(PartnerCompany partnerCompany) {
		if (partnerCompany == null) return;
		if (partnerCompanyMapper.findById_CustomerSupport(partnerCompany.getId()).isEmpty()) return;

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportDModel.update(e));

		partnerCompanyMapper.update_CompensationPlanning(partnerCompany.findVO());
	}

	public void delete_RoadSide(int id) {
		if (partnerCompanyMapper.findById_CustomerSupport(id).isEmpty()) return;
		PartnerCompany partnerCompany = getById_RoadsideCompany(id);

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportDModel.delete(id));

		partnerCompanyMapper.delete_CompensationPlanning(id);
	}

	public void delete_DamageAssesment(int id) {
		if (partnerCompanyMapper.findById_CustomerSupport(id).isEmpty()) return;
		PartnerCompany partnerCompany = getById_DamageAssesmentCompany(id);

		List<Report> reports = partnerCompany.getReportList();
		if (reports != null) reports.forEach(e -> reportDModel.delete(id));

		partnerCompanyMapper.delete_CompensationPlanning(id);
	}
}
