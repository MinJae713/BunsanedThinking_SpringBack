package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CounselVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CounselMapper {
    public List<CounselVO> getAllByCustomerId_Customer(int id);
	void update_SalesModel(CounselVO counselVO);

	CounselVO get_SalesModel(int id);

	ArrayList<CounselVO> getAll_SalesModel();

	public Optional<CounselVO> getById(int id);
	public int getMaxId();
	public void insert(CounselVO counselVO);
	public void update(CounselVO counselVO);
	public void deleteById(int id);
}
