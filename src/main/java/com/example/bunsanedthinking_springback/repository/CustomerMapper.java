package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CustomerVO;

@Mapper
public interface CustomerMapper {
	List<CustomerVO> getAll();

	Optional<CustomerVO> getById(int id);

	void deleteById(int id);

	Integer getMaxId();

	void insert(CustomerVO customerVO);

	void update(CustomerVO customerVO);
}
