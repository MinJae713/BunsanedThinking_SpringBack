package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.LoanVO;

@Mapper
public interface LoanMapper {
	void insert_LoanManagement(LoanVO loanVO);

	Optional<LoanVO> findById_LoanManagement(int id);

	void update_LoanManagement(LoanVO loanVO);

	void delete_LoanManagement(int id);

	Integer getMaxId_LoanManagement();
}
