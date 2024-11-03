package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ReportVO;

@Mapper
public interface ReportMapper {
	void insert_CustomerSupport(ReportVO reportVO);
}
