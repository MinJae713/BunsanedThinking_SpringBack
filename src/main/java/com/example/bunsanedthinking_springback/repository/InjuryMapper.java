package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InjuryVO;

@Mapper
public interface InjuryMapper {
	public Optional<InjuryVO> getById_Customer(int id);

	public List<InjuryVO> getAll_Customer();

	ArrayList<InjuryVO> getAllInjuryInsurance_SalesModel();

	void insert_ProductManagement(InjuryVO injuryVO);

	void update_ProductManagementModel(InjuryVO injuryVO);

	Optional<InjuryVO> findById_FinancialAccountant(int id);

	InjuryVO getById_ProductManagementModel(int id);

	public void deleteById(int id);

	public Integer getMaxId();
}
