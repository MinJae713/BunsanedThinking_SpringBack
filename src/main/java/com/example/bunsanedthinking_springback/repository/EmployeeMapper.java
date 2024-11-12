package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.EmployeeVO;

@Mapper
public interface EmployeeMapper {
	void insert(EmployeeVO employeeVO);

	void deleteById(int id);

	Optional<EmployeeVO> getById(int id);

	void update(EmployeeVO employeeVO);

	List<EmployeeVO> getAll();

	Integer getMaxId();
}
