package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CustomerMapper {
	public Optional<CustomerVO> getById(int id);
	public List<CustomerVO> getAll();
	public Integer getMaxId();
	public void insert(CustomerVO customerVO);
	public void update(CustomerVO customerVO);
	public void deleteById(int id);
}
