package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.RevivalVO;

@Mapper
public interface RevivalMapper {
	Optional<RevivalVO> getById(int id);

	List<RevivalVO> getAll();

	Integer getMaxId();

	void insert(RevivalVO revivalVO);

	void update(RevivalVO revivalVO);

	void deleteById(int id);
}
