package com.example.bunsanedthinking_springback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ServiceVO;

@Mapper
public interface ServiceMapper {
    List<ServiceVO> getById(int id);
	void insert(ServiceVO serviceVO);
	void deleteById(int id);
}
