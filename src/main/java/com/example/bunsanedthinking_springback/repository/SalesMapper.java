package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.SalesVO;

@Mapper
public interface SalesMapper {
	SalesVO get_SalesModel(int id);

	void updateEvaluate_SalesModel(int id, int evaluate);
}
