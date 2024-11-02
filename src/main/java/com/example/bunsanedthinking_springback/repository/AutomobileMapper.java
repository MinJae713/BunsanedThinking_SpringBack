package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AutoMobileVO;

@Mapper
public interface AutomobileMapper {
	Optional<AutoMobileVO> findById_FinancialAccountant(int id);
}
