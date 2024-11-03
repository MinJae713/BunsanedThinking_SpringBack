package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;

@Mapper
public interface SurgeryHistoryMapper {
	ArrayList<SurgeryHistoryVO> get_UnderWritingModel(int id);

	void insert_SalesModel(SurgeryHistoryVO surgeryHistoryVO);
}
