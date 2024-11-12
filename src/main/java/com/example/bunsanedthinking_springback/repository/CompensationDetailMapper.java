package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Optional;

@Mapper
public interface CompensationDetailMapper {
	ArrayList<CompensationDetailVO> getAll();
	Integer getMaxId();
	void insert(CompensationDetailVO compensationDetailVO);
	public Optional<CompensationDetailVO> getById(int id);
	public void update(CompensationDetailVO compensationDetailVO);
	public void deleteById(int id);
}
