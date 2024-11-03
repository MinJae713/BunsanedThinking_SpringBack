package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface DiseaseHistoryMapper {
    public List<DiseaseHistory> getTst();

	ArrayList<DiseaseHistoryVO> get_UnderWritingModel(int id);

	void insert_SalesModel(DiseaseHistoryVO diseaseHistoryVO);
	// 테스트
}
