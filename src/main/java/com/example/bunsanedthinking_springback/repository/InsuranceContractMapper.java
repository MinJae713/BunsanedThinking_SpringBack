package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;

@Mapper
public interface InsuranceContractMapper {
	void insert_LoanManagement(@Param("insuranceContract") InsuranceContractVO insuranceContractVO);

	ArrayList<InsuranceContractVO> getAllInsuranceContractLoan_SalesModel();
}