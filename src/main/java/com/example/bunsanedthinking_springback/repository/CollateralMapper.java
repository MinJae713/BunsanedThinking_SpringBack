package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CollateralVO;

@Mapper
public interface CollateralMapper {
	public Optional<CollateralVO> getById_Customer(int id);

	public List<CollateralVO> getAll_Customer();

	ArrayList<CollateralVO> getAllCollateralLoan_SalesModel();

	void insert_LoanManagement(CollateralVO collateralVO);

	Optional<CollateralVO> findById_LoanManagement(int id);

	void update_LoanManagement(CollateralVO collateralVO);

	void delete_LoanManagement(int id);

	public Integer getMaxId();
}
