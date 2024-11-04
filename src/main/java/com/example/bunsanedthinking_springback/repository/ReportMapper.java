package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ReportVO;

@Mapper
public interface ReportMapper {
	void insert_CustomerSupport(ReportVO reportVO);

	Optional<ReportVO> findById_Compensation(int id);

	void update_Compensation(ReportVO reportVO);

	List<ReportVO> getAll_Compensation();

	List<ReportVO> findByProcessStatus_Compensation(int status);
}
