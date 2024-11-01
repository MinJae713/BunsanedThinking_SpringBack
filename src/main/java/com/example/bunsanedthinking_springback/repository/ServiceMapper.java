package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ServiceVO;

@Mapper
public interface ServiceMapper {
	ArrayList<ServiceVO> get_SalesModel(int id);
}
