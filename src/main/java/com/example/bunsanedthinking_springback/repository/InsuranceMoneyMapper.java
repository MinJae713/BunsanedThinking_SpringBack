package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;

@Mapper
public interface InsuranceMoneyMapper {
	public List<InsuranceMoneyVO> getAllByContractId_Customer(int id);

	public List<InsuranceMoneyVO> getAll_Compensation();

	public Optional<InsuranceMoneyVO> getById_Compensation(int id);

	ArrayList<InsuranceMoneyVO> getAll_UnderwritingModel();

	List<InsuranceMoneyVO> findByContractId_FinancialAccountant(int id);

	public void updateStatus_Compensation(int status, int insuranceMoneyId);

	public Integer getMaxId();

	public void insert(InsuranceMoneyVO insuranceMoneyVO);

	public void update(InsuranceMoneyVO insuranceMoneyVO);

	public void deleteById(int id);
}
