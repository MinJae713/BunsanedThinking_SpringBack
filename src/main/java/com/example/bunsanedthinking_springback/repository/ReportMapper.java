package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.ReportVO;

@Mapper
public interface ReportMapper {
	public List<ReportVO> getAll_Compensation();

	public Optional<ReportVO> getById_Compensation(int id);

	public void updateStatus_Compensation(int processStatus, int reportId);

	void insert_CustomerSupport(ReportVO reportVO);

	void update(ReportVO reportVO);

	List<ReportVO> findAllByDamageAssessmentCompanyID_PartnerCompany(@Param("id") int id);

	ReportVO findById_PartnerCompany(@Param("id") int id);

	void updateReport_PartnerCompany(ReportVO reportVO);

	public Integer getMaxId();

	public void deleteById(int id);

	public List<ReportVO> getAllByRoadSideAssistanceCId(int id);
}

