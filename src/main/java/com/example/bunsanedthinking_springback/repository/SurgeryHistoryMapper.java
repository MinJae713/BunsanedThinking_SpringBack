package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface SurgeryHistoryMapper {
    public List<SurgeryHistoryVO> getAllByCustomerId_Customer(int id);
	ArrayList<SurgeryHistoryVO> get_UnderWritingModel(int id);
	void insert_SalesModel(SurgeryHistoryVO surgeryHistoryVO);

    public void insert_surgeryHistory_CustomerInformationManagement(@Param("surgeryHistory") SurgeryHistoryVO surgeryHistoryVO);
    public void deleteSurgeryHistoriesByCustomerId_CustomerInformationManagement(int customerId);

	Integer getMaxId_SalesModel();
    public void update_surgeryHistory_CustomerInformationManagement(@Param("surgeryHistory") SurgeryHistoryVO surgeryHistoryVO);
    public List<SurgeryHistoryVO> findSurgeryHistoriesByCustomerId_CustomerInformationManagement(int customerId);

    public Optional<SurgeryHistoryVO> getById(int id);
    public List<SurgeryHistoryVO> getAll();
    public void update(SurgeryHistoryVO surgeryHistoryVO);
    public void deleteById(int id);
}
