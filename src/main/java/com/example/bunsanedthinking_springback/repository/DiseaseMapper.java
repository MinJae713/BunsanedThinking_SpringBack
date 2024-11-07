package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DiseaseVO;

@Mapper
public interface DiseaseMapper {
	public Optional<DiseaseVO> getById_Customer(int id);

	public List<DiseaseVO> getAll_Customer();

	ArrayList<DiseaseVO> getAllDiseaseInsurance_SalesModel();

	void insert_ProductManagement(DiseaseVO diseaseVO);

	void update_ProductManagementModel(DiseaseVO diseaseVO);

	Optional<DiseaseVO> findById_FinancialAccountant(int id);

	DiseaseVO getById_ProductManagementModel(int id);

	public void deleteById(int id);

	public Integer getMaxId();
}
