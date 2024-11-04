package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InsuranceContractMapper {
	void insert_LoanManagement(@Param("insuranceContract") InsuranceContractVO insuranceContractVO);

    public Optional<InsuranceContractVO> getInsuranceContractById_Customer(int id);
    public List<InsuranceContractVO> getAll_Customer();
}
