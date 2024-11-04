package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import java.util.ArrayList;
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
}
