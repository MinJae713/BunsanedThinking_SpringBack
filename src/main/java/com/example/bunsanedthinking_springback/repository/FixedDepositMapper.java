package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.FixedDepositVO;

@Mapper
public interface FixedDepositMapper {
	void insert_LoanManagement(FixedDepositVO fixedDepositVO);

	Optional<FixedDepositVO> findById_LoanManagement(int id);

	void delete_LoanManagement(int id);

	void update_LoanManagement(FixedDepositVO fixedDepositVO);
}
