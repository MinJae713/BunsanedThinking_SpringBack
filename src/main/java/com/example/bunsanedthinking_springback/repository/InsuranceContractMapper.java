package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

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
	ArrayList<InsuranceContractVO> getAllInsuranceContractLoan_SalesModel();
	void insert_LoanManagement(InsuranceContractVO insuranceContractVO);

	Optional<InsuranceContractVO> findById_LoanManagement(int id);

	void delete_LoanManagement(int id);

	void update_LoanManagement(InsuranceContractVO insuranceContractVO);
}
