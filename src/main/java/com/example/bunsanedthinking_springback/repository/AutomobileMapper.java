package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AutoMobileVO;

@Mapper
public interface AutomobileMapper {
	public Optional<AutoMobileVO> getById(int id);
	public List<AutoMobileVO> getAll();
	void insert(AutoMobileVO automobileVO);
	void update(AutoMobileVO automobileVO);
	public void deleteById(int id);
	public Integer getMaxId();
}
