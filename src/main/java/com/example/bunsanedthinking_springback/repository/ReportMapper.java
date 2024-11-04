package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.report.Report;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReportMapper {
    List<Report> findAllByDamageAssessmentCompanyID_PartnerCompany(@Param("id") int id);
    Report findById_PartnerCompany(@Param("id") int id);
    void update_PartnerCompany(Report report);
}

