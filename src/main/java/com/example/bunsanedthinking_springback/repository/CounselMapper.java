package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CounselVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.Optional;

@Mapper
public interface CounselMapper {
	public Optional<CounselVO> getById(int id);
	public ArrayList<CounselVO> getAll();
	public Integer getMaxId();
	public void insert(CounselVO counselVO);
	public void update(CounselVO counselVO);
	public void deleteById(int id);
}
