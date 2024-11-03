package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.EmployeeVO;

@Mapper
public interface EmployeeMapper {
	Integer getMaxId_HumanResource();

	void insert_HumanResource(EmployeeVO employeeVO);

	void delete_HumanResource(int id);

	Optional<EmployeeVO> findById_HumanResource(int id);

	void update_HumanResource(EmployeeVO employeeVO);

	List<EmployeeVO> getAll_HumanResource();
}
