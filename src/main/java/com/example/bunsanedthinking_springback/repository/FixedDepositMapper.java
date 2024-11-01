package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.FixedDepositVO;

@Mapper
public interface FixedDepositMapper {
	void insert_LoanManagement(@Param("fixedDeposit") FixedDepositVO fixedDepositVO);

	ArrayList<FixedDepositVO> getAllFixedDepositLoan_SalesModel();
}
