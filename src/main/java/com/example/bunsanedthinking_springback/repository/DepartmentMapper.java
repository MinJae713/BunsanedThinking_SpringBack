package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.DepartmentVO;

@Mapper
public interface DepartmentMapper {
	List<DepartmentVO> getAll_HumanResource();

	Optional<DepartmentVO> findById_HumanResource(int departmentID);
}
