package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;

@Mapper
public interface DiseaseHistoryMapper {
	void insert(DiseaseHistoryVO diseaseHistoryVO);

	Integer getMaxId();

	Optional<DiseaseHistoryVO> getById(int id);

	List<DiseaseHistoryVO> getAll();

	void update(DiseaseHistoryVO diseaseHistoryVO);

	void deleteById(int id);
}
