package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AutomobileVO;

@Mapper
public interface AutomobileMapper {
	ArrayList<AutomobileVO> getAllAutomobileInsurance_SalesModel();

	void insert_ProductManagement(AutomobileVO automobileVO);

	void update_ProductManagementModel(AutomobileVO automobileVO);
}
