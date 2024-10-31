package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.LoanVO;

@Mapper
public interface LoanMapper {
	void insert_LoanManagement(@Param("loan") LoanVO loanVO);
}
