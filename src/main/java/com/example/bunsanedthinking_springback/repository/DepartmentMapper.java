package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DepartmentMapper {
	public Optional<DepartmentVO> getById(int id);
	public List<DepartmentVO> getAll();
	public Integer getMaxId();
	public void insert(DepartmentVO departmentVO);
	public void update(DepartmentVO departmentVO);
	public void deleteById(int id);
}
