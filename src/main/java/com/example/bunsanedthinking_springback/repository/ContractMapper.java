package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ContractVO;

@Mapper
public interface ContractMapper {
	Optional<ContractVO> getById(int id);

	List<ContractVO> getAll();

	void insert(ContractVO contractVO);

	void update(ContractVO contractVO);

	Integer getMaxId();

	void deleteById(int id);
}
