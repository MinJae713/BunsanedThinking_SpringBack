package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DepositDetailVO;

@Mapper
public interface DepositDetailMapper {
	Integer getMaxId();

	void insert(DepositDetailVO depositDetailVO);

	Optional<DepositDetailVO> getById(int id);

	List<DepositDetailVO> getAll();

	void update(DepositDetailVO depositDetailVO);

	void deleteById(int id);
}
