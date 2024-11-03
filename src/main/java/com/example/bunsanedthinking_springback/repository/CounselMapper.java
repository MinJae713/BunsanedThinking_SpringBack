package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CounselVO;

@Mapper
public interface CounselMapper {
	void update_SalesModel(CounselVO counselVO);

	CounselVO get_SalesModel(int id);

	ArrayList<CounselVO> getAll_SalesModel();
}
