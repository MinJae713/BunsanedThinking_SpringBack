package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ReportVO;

@Mapper
public interface ReportMapper {
	List<ReportVO> getAll();

	Optional<ReportVO> getById(int id);

	void insert(ReportVO reportVO);

	void update(ReportVO reportVO);

	void updateReport(ReportVO reportVO);

	Integer getMaxId();

	void deleteById(int id);

}

