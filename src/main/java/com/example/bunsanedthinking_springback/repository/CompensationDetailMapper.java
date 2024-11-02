package com.example.bunsanedthinking_springback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;

@Mapper
public interface CompensationDetailMapper {
	Integer getMaxId_LoanManagement();

	void insert_LoanManagement(CompensationDetailVO compensationDetailVO);

	List<CompensationDetailVO> findByContractId_FinancialAccountant(int id);
}
