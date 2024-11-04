package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

import com.example.bunsanedthinking_springback.vo.InsuranceVO;

@Mapper
public interface InsuranceMapper {
    public Optional<InsuranceVO> getInsuranceById_Customer(int id);
    public List<InsuranceVO> getAll_Customer();
	InsuranceVO get_SalesModel(int id);
	void insert_ProductManagement(@Param("insurance")InsuranceVO insuranceVO);
	void update_ProductManagementModel(InsuranceVO insuranceVO);
	Optional<InsuranceVO> findById_FinancialAccountant(int id);
}
