package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.EndorsementVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EndorsementMapper {
	public Optional<EndorsementVO> getById(int id);
	public List<EndorsementVO> getAll();
	public Integer getMaxId();
	public void insert(EndorsementVO endorsementVO);
	public void update(EndorsementVO endorsementVO);
	public void deleteById(int id);
}
