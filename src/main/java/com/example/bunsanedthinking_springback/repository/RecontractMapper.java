package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.RecontractVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RecontractMapper {
	public Optional<RecontractVO> getById(int id);
	public List<RecontractVO> getAll();
	public Integer getMaxId();
	public void insert(RecontractVO recontractVO);
	public void update(RecontractVO recontractVO);
	public void deleteById(int id);
}
