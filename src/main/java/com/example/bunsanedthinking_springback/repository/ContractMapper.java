package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ContractVO;

@Mapper
public interface ContractMapper {
	Optional<ContractVO> findById_FinancialAccountant(int id);
}
