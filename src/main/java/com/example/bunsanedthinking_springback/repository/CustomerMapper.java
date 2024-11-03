package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CustomerVO;

@Mapper
public interface CustomerMapper {
	CustomerVO get_UnderWritingModel(int id);

	void insert_SalesModel(CustomerVO customerVO);

	CustomerVO get_SalesModel(int id);
}
