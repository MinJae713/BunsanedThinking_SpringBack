package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.EndorsementVO;

@Mapper
public interface EndorsementMapper {
	Optional<EndorsementVO> getById(int id);

	List<EndorsementVO> getAll();

	Integer getMaxId();

	void insert(EndorsementVO endorsementVO);

	void update(EndorsementVO endorsementVO);

	void deleteById(int id);
}
