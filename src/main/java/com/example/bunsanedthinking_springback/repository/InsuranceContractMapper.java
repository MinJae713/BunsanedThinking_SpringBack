package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;

@Mapper
public interface InsuranceContractMapper {
	void insert_LoanManagement(InsuranceContractVO insuranceContractVO);

	Optional<InsuranceContractVO> findById_LoanManagement(int id);

	void delete_LoanManagement(int id);

	void update_LoanManagement(InsuranceContractVO insuranceContractVO);
}
