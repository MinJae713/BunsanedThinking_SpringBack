package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.CollateralVO;

@Mapper
public interface CollateralMapper {
	void insert_LoanManagement(@Param("collateral") CollateralVO collateralVO);

	ArrayList<CollateralVO> getAllCollateralLoan_SalesModel();
}
