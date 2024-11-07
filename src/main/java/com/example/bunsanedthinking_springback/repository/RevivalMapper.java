package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.RevivalVO;

@Mapper
public interface RevivalMapper {
	public Optional<RevivalVO> getById_Customer(int id);

	public void addById_Customer(int contractId);

	public Optional<RevivalVO> getById_ContractManagement(int id);

	public List<RevivalVO> getAll_ContractManagement();

	public void updateStatus_ContractManagement(int status, int id);

	public Integer getMaxId();

	public void insert(RevivalVO revivalVO);

	public void update(RevivalVO revivalVO);

	public void deleteById(int id);
}
