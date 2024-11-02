package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InjuryVO;

@Mapper
public interface InjuryMapper {
	Optional<InjuryVO> findById_FinancialAccountant(int id);
}
