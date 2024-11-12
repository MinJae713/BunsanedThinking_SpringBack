package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.SalesVO;

@Mapper
public interface SalesMapper {
	SalesVO getById(int id);

	ArrayList<SalesVO> getAll();

	Integer getMaxId();

	void update(SalesVO salesVO);

	void insert(SalesVO salesVO);

	void deleteById(int id);
}
