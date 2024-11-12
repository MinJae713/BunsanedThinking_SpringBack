package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ContractVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ContractMapper {
	public Optional<ContractVO> getById(int id);
	public List<ContractVO> getAll();
	public Integer getMaxId();
	public void insert(ContractVO contractVO);
	public void update(ContractVO contractVO);
	public void deleteById(int id);
}
