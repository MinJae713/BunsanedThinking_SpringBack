package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;

@Mapper
public interface InsuranceMoneyMapper {
	ArrayList<InsuranceMoneyVO> getAll_UnderwritingModel();
}
