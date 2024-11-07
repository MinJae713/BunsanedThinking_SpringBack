package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface InsuranceContractMapper {
    public Optional<InsuranceContractVO> getById_Customer(int id);
    public List<InsuranceContractVO> getAll_Customer();
	ArrayList<InsuranceContractVO> getAllInsuranceContractLoan_SalesModel();
	void insert_LoanManagement(InsuranceContractVO insuranceContractVO);

	Optional<InsuranceContractVO> findById_LoanManagement(int id);

	void delete_LoanManagement(int id);

	void update_LoanManagement(InsuranceContractVO insuranceContractVO);

	public int getMaxId();
}
