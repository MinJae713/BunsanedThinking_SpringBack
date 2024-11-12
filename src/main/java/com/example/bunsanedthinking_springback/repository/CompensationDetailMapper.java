package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CompensationDetailMapper {
	public Optional<CompensationDetailVO> getById(int id);
	public List<CompensationDetailVO> getAll();
	public Integer getMaxId();
	public void insert(CompensationDetailVO compensationDetailVO);
	public void update(CompensationDetailVO compensationDetailVO);
	public void deleteById(int id);
}
