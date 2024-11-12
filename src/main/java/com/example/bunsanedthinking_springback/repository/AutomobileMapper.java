package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.AutoMobileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AutomobileMapper {
	public Optional<AutoMobileVO> getById(int id);
	public List<AutoMobileVO> getAll();
	public Integer getMaxId();
	public void insert(AutoMobileVO automobileVO);
	public void update(AutoMobileVO automobileVO);
	public void deleteById(int id);
}
