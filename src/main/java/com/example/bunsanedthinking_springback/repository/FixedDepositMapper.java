package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.FixedDepositVO;

@Mapper
public interface FixedDepositMapper {
	public Optional<FixedDepositVO> getById_Customer(int id);

	public List<FixedDepositVO> getAll_Customer();

	ArrayList<FixedDepositVO> getAllFixedDepositLoan_SalesModel();

	void insert_LoanManagement(FixedDepositVO fixedDepositVO);

	Optional<FixedDepositVO> findById_LoanManagement(int id);

	void delete_LoanManagement(int id);

	void update_LoanManagement(FixedDepositVO fixedDepositVO);

	public Integer getMaxId();
}
