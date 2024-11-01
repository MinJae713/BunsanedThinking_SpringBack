package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.CollateralVO;

@Mapper
public interface CollateralMapper {
	void insert_LoanManagement(CollateralVO collateralVO);

	Optional<CollateralVO> findById_LoanManagement(int id);

	void update_LoanManagement(CollateralVO collateralVO);

	void delete_LoanManagement(int id);
}
