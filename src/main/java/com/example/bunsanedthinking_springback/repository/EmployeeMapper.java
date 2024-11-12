package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper {
	public Optional<EmployeeVO> getById(int id);
	public List<EmployeeVO> getAll();
	public Integer getMaxId();
	public void insert(EmployeeVO employeeVO);
	public void update(EmployeeVO employeeVO);
	public void deleteById(int id);
}
