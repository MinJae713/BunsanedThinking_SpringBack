package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ComplaintVO;

@Mapper
public interface ComplaintMapper {
	public Optional<ComplaintVO> getById(int id);
	void update(ComplaintVO complaintVO);
	List<ComplaintVO> getAll();
	public Integer getMaxId();
	public void insert(ComplaintVO complaintVO);
	public void deleteById(int id);
}
