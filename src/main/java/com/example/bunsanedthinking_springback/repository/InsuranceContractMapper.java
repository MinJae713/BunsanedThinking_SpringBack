package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InsuranceContractMapper {
	public Optional<InsuranceContractVO> getById(int id);
	public List<InsuranceContractVO> getAll();
	public void insert(InsuranceContractVO insuranceContractVO);
	public void deleteById(int id);
	public void update(InsuranceContractVO insuranceContractVO);
	public Integer getMaxId();
}
