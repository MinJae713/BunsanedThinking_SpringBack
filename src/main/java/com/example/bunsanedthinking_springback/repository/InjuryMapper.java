package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InjuryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InjuryMapper {
	public Optional<InjuryVO> getById(int id);
	public List<InjuryVO> getAll();
	public void insert(InjuryVO injuryVO);
	public void update(InjuryVO injuryVO);
	public void delete(int id);
	public Integer getMaxId();
}
