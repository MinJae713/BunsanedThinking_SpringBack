package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.RecontractVO;

@Mapper
public interface RecontractMapper {
	Optional<RecontractVO> getById(int id);

	List<RecontractVO> getAll();

	Integer getMaxId();

	void insert(RecontractVO recontractVO);

	void update(RecontractVO recontractVO);

	void deleteById(int id);
}
