package com.example.bunsanedthinking_springback.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.EmployeeVO;

@Mapper
public interface EmployeeMapper {
	ArrayList<EmployeeVO> getAll_SalesModel();

	EmployeeVO get_SalesModel(int id);
}
