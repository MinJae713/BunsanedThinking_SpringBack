package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.InjuryVO;

@Mapper
public interface InjuryMapper {
	ArrayList<InjuryVO> getAllInjuryInsurance_SalesModel();
}
