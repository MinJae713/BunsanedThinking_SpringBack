package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.TerminationVO;

@Mapper
public interface TerminationMapper {
	Optional<TerminationVO> getById(int id);

	List<TerminationVO> getAll();

	Integer getMaxId();

	void insert(TerminationVO terminationVO);

	void update(TerminationVO terminationVO);

	void deleteById(int id);
}
