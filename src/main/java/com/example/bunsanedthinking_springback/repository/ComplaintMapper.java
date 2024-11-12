package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ComplaintVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ComplaintMapper {
	public Optional<ComplaintVO> getById(int id);
	public List<ComplaintVO> getAll();
	public Integer getMaxId();
	public void update(ComplaintVO complaintVO);
	public void insert(ComplaintVO complaintVO);
	public void deleteById(int id);
}
