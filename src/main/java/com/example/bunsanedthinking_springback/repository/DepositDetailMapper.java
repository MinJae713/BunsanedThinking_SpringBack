package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DepositDetailVO;

@Mapper
public interface DepositDetailMapper {
	public List<DepositDetailVO> getAllDepositByContractId_Customer(int id);

	public Integer getLastId_Customer();

	public int getCount_Customer();

	public void add_Customer(DepositDetailVO depositDetailVO);

	ArrayList<DepositDetailVO> getAll_UnderwritingModel();

	Optional<DepositDetailVO> findById_FinancialAccountant(int id);

	List<DepositDetailVO> getAll_FinancialAccountant();

	List<DepositDetailVO> findByContractId_FinancialAccountant(int id);

	public List<DepositDetailVO> getAll_ContractManagement();

	public void update(DepositDetailVO depositDetailVO);

	public void deleteById(int id);
}
