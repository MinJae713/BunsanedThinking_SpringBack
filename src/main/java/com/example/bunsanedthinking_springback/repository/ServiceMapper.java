package com.example.bunsanedthinking_springback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ServiceVO;

@Mapper
public interface ServiceMapper {
	List<ServiceVO> findByProductId_FinancialAccountant(int id);
}
