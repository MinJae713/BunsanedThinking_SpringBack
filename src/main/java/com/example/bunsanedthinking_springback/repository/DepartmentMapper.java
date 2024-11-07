package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DepartmentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface DepartmentMapper {
	List<DepartmentVO> getAll_HumanResource();
	Optional<DepartmentVO> findById_HumanResource(int departmentID);

    public void insert_ManagementPlanning(DepartmentVO departmentVO);
    public void delete_ManagementPlanning(int id);
    public DepartmentVO findById_ManagementPlanning(int id);
    public DepartmentVO findByName_ManagementPlanning(String name);
    public void update_ManagementPlanning(DepartmentVO departmentVO);

    Integer getMaxId_ManagementPlanning();

    public void insert(DepartmentVO departmentVO);
}
