package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InsuranceMapper {
    public List<InsuranceVO> getAll();
	public void insert(InsuranceVO insuranceVO);
	public void update(InsuranceVO insuranceVO);
	public Optional<InsuranceVO> getById(int id);
	public Integer getMaxId();
	public void deleteById(int id);
}
