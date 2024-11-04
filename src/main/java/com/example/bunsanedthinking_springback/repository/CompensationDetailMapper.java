package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;

@Mapper
public interface CompensationDetailMapper {
    public List<CompensationDetailVO> getAllCompensationByContractId_Customer(int id);
	ArrayList<CompensationDetailVO> getAll_UnderwritingModel();
}
