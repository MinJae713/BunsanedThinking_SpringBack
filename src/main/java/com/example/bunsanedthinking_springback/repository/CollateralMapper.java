package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CollateralVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CollateralMapper {
	public Optional<CollateralVO> getById(int id);
	public List<CollateralVO> getAll();
	public Integer getMaxId();
	public void insert(CollateralVO collateralVO);
	public void update(CollateralVO collateralVO);
	public void deleteById(int id);
}
