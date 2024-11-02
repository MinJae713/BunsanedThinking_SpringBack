package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.InsuranceVO;

@Mapper
public interface InsuranceMapper {
	InsuranceVO get_SalesModel(int id);

	void insert_ProductManagement(@Param("insurance")InsuranceVO insuranceVO);

	void update_ProductManagementModel(InsuranceVO insuranceVO);
}
