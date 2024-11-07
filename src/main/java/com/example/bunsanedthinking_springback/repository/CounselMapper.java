package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.CounselVO;

@Mapper
public interface CounselMapper {
	public List<CounselVO> getAllByCustomerId_Customer(int id);

	void update_SalesModel(CounselVO counselVO);

	CounselVO get_SalesModel(int id);

	ArrayList<CounselVO> getAll_SalesModel();

	public Optional<CounselVO> getById(int id);

	public Integer getMaxId();

	public void insert(CounselVO counselVO);

	public void update(CounselVO counselVO);

	public void deleteById(int id);
}
