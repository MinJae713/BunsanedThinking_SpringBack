package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DepositDetailVO;

@Mapper
public interface DepositDetailMapper {
	Optional<DepositDetailVO> findById_FinancialAccountant(int id);

	List<DepositDetailVO> getAll_FinancialAccountant();
}
