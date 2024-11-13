package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;

@Mapper
public interface DiseaseHistoryMapper {
	public Optional<DiseaseHistoryVO> getById(int id);
	public List<DiseaseHistoryVO> getAll();
	public Integer getMaxId();
	public void insert(DiseaseHistoryVO diseaseHistoryVO);
	public void update(DiseaseHistoryVO diseaseHistoryVO);
	public void deleteById(int id);

}
