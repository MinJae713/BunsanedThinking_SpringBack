package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Mapper
public interface InsuranceMoneyMapper {
    public List<InsuranceMoneyVO> getAllByContractId_Customer(int id);

    public List<InsuranceMoneyVO> getAll_Compensation();

    public Optional<InsuranceMoneyVO> getById_Compensation(int id);
	ArrayList<InsuranceMoneyVO> getAll_UnderwritingModel();
	List<InsuranceMoneyVO> findByContractId_FinancialAccountant(int id);

    public void updateStatus_Compensation(int status, int insuranceMoneyId);
}
