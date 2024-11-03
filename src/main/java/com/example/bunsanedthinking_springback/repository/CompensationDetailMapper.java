package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;

@Mapper
public interface CompensationDetailMapper {
	ArrayList<CompensationDetailVO> getAll_UnderwritingModel();
}
