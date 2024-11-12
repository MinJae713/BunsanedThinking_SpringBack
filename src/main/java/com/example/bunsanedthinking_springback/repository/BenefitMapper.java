package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.BenefitVO;

@Mapper
public interface BenefitMapper {
	public Optional<BenefitVO> getById(int id);
	public List<BenefitVO> getAll();
	public Integer getMaxId();
	public void insert(BenefitVO benefitVO);
	public void update(BenefitVO benefitVO);
	public void deleteById(int id);
}
