package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ReportVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReportMapper {
	public Optional<ReportVO> getById(int id);
	public List<ReportVO> getAll();
	public Integer getMaxId();
	public void insert(ReportVO reportVO);
	public void update(ReportVO reportVO);
	public void deleteById(int id);
}

