package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;

@Mapper
public interface AccidentHistoryMapper {
	ArrayList<AccidentHistoryVO> get_UnderWritingModel(int id);

	void insert_SalesModel(AccidentHistoryVO accidentHistoryVO);
}
