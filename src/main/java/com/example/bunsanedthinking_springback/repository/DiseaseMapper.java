package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DiseaseMapper {
	public Optional<DiseaseVO> getById(int id);
	public List<DiseaseVO> getAll();
	public Integer getMaxId();
	public void insert(DiseaseVO diseaseVO);
	public void update(DiseaseVO diseaseVO);
	public void deleteById(int id);
}
