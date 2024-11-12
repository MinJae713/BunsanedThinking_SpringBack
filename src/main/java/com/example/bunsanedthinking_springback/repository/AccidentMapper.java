package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.AccidentVO;

@Mapper
public interface AccidentMapper {
	public Optional<AccidentVO> getById(int id);
	void update(AccidentVO accidentVO);
	List<AccidentVO> getAll();
	public Integer getMaxId();
	public void insert(AccidentVO accidentVO);
	public void delete(int id);
}
