package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AdditionalAllowanceVO;

@Mapper
public interface AdditionalAllowanceMapper {
	public Optional<AdditionalAllowanceVO> getById(int id);
	public List<AdditionalAllowanceVO> getAll();
	public Integer getMaxId();
	public void insert(AdditionalAllowanceVO additionalAllowanceVO);
	public void update(AdditionalAllowanceVO additionalAllowanceVO);
	public void deleteById(int id);
}
