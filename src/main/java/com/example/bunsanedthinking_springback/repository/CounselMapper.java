package com.example.bunsanedthinking_springback.repository;
import com.example.bunsanedthinking_springback.vo.CounselVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CounselVO;

@Mapper
public interface CounselMapper {
    public List<CounselVO> getAllByCustomerId_Customer(int id);
	void update_SalesModel(CounselVO counselVO);

	CounselVO get_SalesModel(int id);

	ArrayList<CounselVO> getAll_SalesModel();
}
