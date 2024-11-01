package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DiseaseVO;

@Mapper
public interface DiseaseMapper {
	ArrayList<DiseaseVO> getAllDiseaseInsurance_SalesModel();
}
