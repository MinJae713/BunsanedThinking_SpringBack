package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CounselVO;

@Mapper
public interface CounselMapper {
	void update(CounselVO counselVO);

	ArrayList<CounselVO> getAll();

	Optional<CounselVO> getById(int id);

	Integer getMaxId();

	void insert(CounselVO counselVO);

	void deleteById(int id);
}
