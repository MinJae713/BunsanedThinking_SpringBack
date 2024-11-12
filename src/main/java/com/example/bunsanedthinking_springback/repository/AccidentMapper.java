package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.AccidentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AccidentMapper {
	public Optional<AccidentVO> getById(int id);
	List<AccidentVO> getAll();
	public Integer getMaxId();
	void update(AccidentVO accidentVO);
	public void insert(AccidentVO accidentVO);
	public void deleteById(int id);
}
