package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface AccidentHistoryMapper {
    public List<AccidentHistoryVO> getAllByCustomerId_Customer(int id);
    public List<AccidentHistoryVO> getAllByCustomerId_CustomerSupport(int id);
	ArrayList<AccidentHistoryVO> get_UnderWritingModel(int id);
	void insert_SalesModel(AccidentHistoryVO accidentHistoryVO);

    public void insert_accidentHistory_CustomerInformationManagement(@Param("accidentHistory") AccidentHistoryVO accidentHistoryVO);
    public void deleteAccidentHistoriesByCustomerId_CustomerInformationManagement(int customerId);

    public void update_accidentHistory_CustomerInformationManagement(@Param("accidentHistory") AccidentHistoryVO accidentHistoryVO);
    public List<AccidentHistoryVO> findAccidentHistoriesByCustomerId_CustomerInformationManagement(int customerId);
}
