package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FixedDepositMapper {
	void insert_LoanManagement(@Param("fixedDeposit") FixedDepositVO fixedDepositVO);
    public Optional<FixedDepositVO> getFixedDepositById_Customer(int id);
    public List<FixedDepositVO> getAll_Customer();
	ArrayList<FixedDepositVO> getAllFixedDepositLoan_SalesModel();
	void insert_LoanManagement(FixedDepositVO fixedDepositVO);

	Optional<FixedDepositVO> findById_LoanManagement(int id);

	void delete_LoanManagement(int id);

	void update_LoanManagement(FixedDepositVO fixedDepositVO);
}
