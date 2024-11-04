package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.LoanVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LoanMapper {
	void insert_LoanManagement(@Param("loan") LoanVO loanVO);

    public Optional<LoanVO> getLoanById_Customer(int id);
    public List<LoanVO> getAll_Customer();
}
