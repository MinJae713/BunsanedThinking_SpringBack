package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FixedDepositMapper {
	public Optional<FixedDepositVO> getById(int id);
	public List<FixedDepositVO> getAll();
	public void insert(FixedDepositVO fixedDepositVO);
	public void delete(int id);
	public void update(FixedDepositVO fixedDepositVO);
	public Integer getMaxId();
}
