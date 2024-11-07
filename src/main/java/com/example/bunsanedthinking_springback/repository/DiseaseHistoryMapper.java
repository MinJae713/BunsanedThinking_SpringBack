package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface DiseaseHistoryMapper {
    public List<DiseaseHistoryVO> getAllByCustomerId_Customer(int id);
	ArrayList<DiseaseHistoryVO> get_UnderWritingModel(int id);
	void insert_SalesModel(DiseaseHistoryVO diseaseHistoryVO);

    Integer getMaxId_CustomerInformationManagement();
    public void insert_diseaseHistory_CustomerInformationManagement(DiseaseHistoryVO diseaseHistoryVO);
    public void deleteDiseaseHistoriesByCustomerId_CustomerInformationManagement(int customerId);

	void insertExcludedCustomerId_SalesModel(DiseaseHistoryVO diseaseHistoryVO);

	Integer getMaxId_SalesModel();
    public void update_diseaseHistory_CustomerInformationManagement(DiseaseHistoryVO diseaseHistoryVO);
    public List<DiseaseHistoryVO> findDiseaseHistoriesByCustomerId_CustomerInformationManagement(int customerId);

    public Optional<DiseaseHistoryVO> getById(int id);
    public List<DiseaseHistoryVO> getAll();
    public void update(DiseaseHistoryVO diseaseHistoryVO);
    public void deleteById(int id);
}
