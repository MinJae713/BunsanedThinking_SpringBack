package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;

@Mapper
public interface InsuranceMoneyMapper {
	List<InsuranceMoneyVO> findByContractId_FinancialAccountant(int id);

	Optional<InsuranceMoneyVO> findById_Compensation(int id);

	void insert_Compensation(InsuranceMoneyVO insuranceMoneyVO);

	List<InsuranceMoneyVO> getAll_Compensation();

	List<InsuranceMoneyVO> findByStatus_Compensation(int status);
}
