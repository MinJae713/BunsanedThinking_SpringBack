package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.LoanVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
	void insert_LoanManagement(@Param("loan") LoanVO loanVO);
    public Optional<LoanVO> getLoanById_Customer(int id);
    public List<LoanVO> getAll_Customer();
	LoanVO get_SalesModel(int id);
	void insert_LoanManagement(LoanVO loanVO);

	Optional<LoanVO> findById_LoanManagement(int id);

	void update_LoanManagement(LoanVO loanVO);

	void delete_LoanManagement(int id);

	Integer getMaxId_LoanManagement();
}
