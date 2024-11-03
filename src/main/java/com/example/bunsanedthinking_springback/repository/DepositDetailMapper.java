package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DepositDetailVO;

@Mapper
public interface DepositDetailMapper {
	ArrayList<DepositDetailVO> getAll_UnderwritingModel();
}
