package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;

@Mapper
public interface InjuryMapper {
	ArrayList<InjuryVO> getAllInjuryInsurance_SalesModel();

	void insert_ProductManagement(InjuryVO injuryVO);

	void update_ProductManagementModel(InjuryVO injuryVO);
}
