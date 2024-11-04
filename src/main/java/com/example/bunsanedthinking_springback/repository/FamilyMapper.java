package com.example.bunsanedthinking_springback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.FamilyVO;

@Mapper
public interface FamilyMapper {
	Integer getMaxId_HumanResource();

	void insert_HumanResource(FamilyVO familyVO);

	void deleteByEmployeeId_HumanResource(int id);

	List<FamilyVO> findByEmployeeId_HumanResource(int id);
}
