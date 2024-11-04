package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.CollateralVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CollateralMapper {
	void insert_LoanManagement(@Param("collateral") CollateralVO collateralVO);
    public Optional<CollateralVO> getCollateralById_Customer(int id);
    public List<CollateralVO> getAll_Customer();
	ArrayList<CollateralVO> getAllCollateralLoan_SalesModel();
}
