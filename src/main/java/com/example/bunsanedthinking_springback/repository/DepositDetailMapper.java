package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DepositDetailMapper {
  	public Optional<DepositDetailVO> getById(int id);
	public List<DepositDetailVO> getAll();
	public Integer getMaxId();
	public void insert(DepositDetailVO depositDetailVO);
	public void update(DepositDetailVO depositDetailVO);
	public void deleteById(int id);
}
