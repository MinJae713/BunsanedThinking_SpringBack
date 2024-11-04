package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import com.example.bunsanedthinking_springback.entity.department.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.DepartmentVO;

@Mapper
public interface DepartmentMapper {
	List<DepartmentVO> getAll_HumanResource();

	Optional<DepartmentVO> findById_HumanResource(int departmentID);
    public void insert_ManagementPlanning(@Param("department") Department department);
    public void delete_ManagementPlanning(int id);
    public Department findById_ManagementPlanning(int id);
    public Department findByName_ManagementPlanning(String name);
    public void update_ManagementPlanning(@Param("department") Department department);
}
