package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ContractVO;

@Mapper
public interface ContractMapper {
	void insert_SalesModel(ContractVO contractVO);
}
