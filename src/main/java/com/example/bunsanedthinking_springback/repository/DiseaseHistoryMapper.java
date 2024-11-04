package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiseaseHistoryMapper {
    public List<DiseaseHistory> getTst();
    // 테스트

    public void insert_diseaseHistory_CustomerInformationManagement(@Param("diseaseHistory") DiseaseHistory diseaseHistory);
    public void deleteDiseaseHistoriesByCustomerId_CustomerInformationManagement(int customerId);

    public void update_diseaseHistory_CustomerInformationManagement(@Param("diseaseHistory") DiseaseHistory diseaseHistory);
    public List<DiseaseHistory> findDiseaseHistoriesByCustomerId_CustomerInformationManagement(int customerId);
}
