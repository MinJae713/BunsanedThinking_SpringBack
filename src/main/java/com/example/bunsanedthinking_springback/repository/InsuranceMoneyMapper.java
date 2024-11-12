package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InsuranceMoneyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InsuranceMoneyMapper {
	public Optional<InsuranceMoneyVO> getById(int id);
	public List<InsuranceMoneyVO> getAll();
	public Integer getMaxId();
	public void insert(InsuranceMoneyVO insuranceMoneyVO);
	public void update(InsuranceMoneyVO insuranceMoneyVO);
	public void deleteById(int id);
}
