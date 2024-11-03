package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ContractVO;

@Mapper
public interface ContractMapper {
	void insert_SalesModel(ContractVO contractVO);

	ContractVO get_UnderWritingModel(int id);

	ArrayList<ContractVO> getAll_UnderWritingModel();

	void update(ContractVO contractVO);
}
