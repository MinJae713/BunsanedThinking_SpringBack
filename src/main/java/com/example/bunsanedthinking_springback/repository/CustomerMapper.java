package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CustomerVO;

@Mapper
public interface CustomerMapper {
	Optional<CustomerVO> findById_FinancialAccountant(int id);
}
