package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

import java.util.List;
import java.util.ArrayList;

@Mapper
public interface DepositDetailMapper {
    public List<DepositDetailVO> getAllDepositByContractId_Customer(int id);
    public int getLastId_Customer();
    public int getCount_Customer();
    public void add_Customer(DepositDetailVO depositDetailVO);
	ArrayList<DepositDetailVO> getAll_UnderwritingModel();
    Optional<DepositDetailVO> findById_FinancialAccountant(int id);

	List<DepositDetailVO> getAll_FinancialAccountant();

	List<DepositDetailVO> findByContractId_FinancialAccountant(int id);
}
