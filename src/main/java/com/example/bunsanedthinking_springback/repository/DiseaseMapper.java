package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DiseaseVO;

@Mapper
public interface DiseaseMapper {
	Optional<DiseaseVO> findById_FinancialAccountant(int id);
}
