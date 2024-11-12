package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CollateralVO;

@Mapper
public interface CollateralMapper {
	public Optional<CollateralVO> getById(int id);
	public List<CollateralVO> getAll();
	void insert(CollateralVO collateralVO);
	void update(CollateralVO collateralVO);
	void delete(int id);
	public Integer getMaxId();
}
