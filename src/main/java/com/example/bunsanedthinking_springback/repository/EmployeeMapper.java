package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface EmployeeMapper {
	ArrayList<EmployeeVO> getAll_SalesModel();

	EmployeeVO get_SalesModel(int id);

	void insert_HumanResource(EmployeeVO employeeVO);

	void delete_HumanResource(int id);

	Optional<EmployeeVO> findById_HumanResource(int id);

	void update_HumanResource(EmployeeVO employeeVO);

	List<EmployeeVO> getAll_HumanResource();

	Integer isExistResidentRegistrationNumber(String residentRegistrationNumber);

	public int getMaxId();
}
