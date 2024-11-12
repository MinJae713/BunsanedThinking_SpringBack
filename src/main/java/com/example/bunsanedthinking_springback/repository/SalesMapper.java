package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.SalesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SalesMapper {
	public Optional<SalesVO> getById(int id);
	public List<SalesVO> getAll();
	public Integer getMaxId();
	public void insert(SalesVO salesVO);
	public void update(SalesVO salesVO);
	public void deleteById(int id);
}
