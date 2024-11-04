package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;
import java.util.ArrayList;

@Mapper
public interface SurgeryHistoryMapper {
    public List<SurgeryHistoryVO> getAllByCustomerId_Customer(int id);
	ArrayList<SurgeryHistoryVO> get_UnderWritingModel(int id);
	void insert_SalesModel(SurgeryHistoryVO surgeryHistoryVO);
}
    public void insert_surgeryHistory_CustomerInformationManagement(@Param("surgeryHistory") SurgeryHistory surgeryHistory);
    public void deleteSurgeryHistoriesByCustomerId_CustomerInformationManagement(int customerId);

    public void update_surgeryHistory_CustomerInformationManagement(@Param("surgeryHistory") SurgeryHistory surgeryHistory);
    public List<SurgeryHistory> findSurgeryHistoriesByCustomerId_CustomerInformationManagement(int customerId);
}
