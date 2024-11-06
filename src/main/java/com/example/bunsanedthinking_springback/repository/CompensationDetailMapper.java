package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CompensationDetailMapper {
    public List<CompensationDetailVO> getAllCompensationByContractId_Customer(int id);
	ArrayList<CompensationDetailVO> getAll_UnderwritingModel();
	Integer getMaxId_LoanManagement();

	void insert_LoanManagement(CompensationDetailVO compensationDetailVO);

	List<CompensationDetailVO> findByContractId_FinancialAccountant(int id);

	public Optional<CompensationDetailVO> getById(int id);
	public void update(CompensationDetailVO compensationDetailVO);
	public void deleteById(int id);
}
