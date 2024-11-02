package com.example.bunsanedthinking_springback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;

@Mapper
public interface InsuranceMoneyMapper {
	List<InsuranceMoneyVO> findByContractId_FinancialAccountant(int id);
}
