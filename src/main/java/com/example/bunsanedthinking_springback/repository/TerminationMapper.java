package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.TerminationVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TerminationMapper {
	public Optional<TerminationVO> getById(int id);
	public List<TerminationVO> getAll();
	public Integer getMaxId();
	public void insert(TerminationVO terminationVO);
	public void update(TerminationVO terminationVO);
	public void deleteById(int id);
}
