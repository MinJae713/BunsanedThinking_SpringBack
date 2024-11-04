package com.example.bunsanedthinking_springback.repository;
import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AutomobileVO;

@Mapper
public interface AutomobileMapper {
    public Optional<AutoMobileVO> getAutoMobileById_Customer(int id);
    public List<AutoMobileVO> getAll_Customer();
		ArrayList<AutomobileVO> getAllAutomobileInsurance_SalesModel();

		void insert_ProductManagement(AutomobileVO automobileVO);

		void update_ProductManagementModel(AutomobileVO automobileVO);
}
