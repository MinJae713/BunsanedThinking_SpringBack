package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.department.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DepartmentMapper {
    public void insert_ManagementPlanning(@Param("department") Department department);
    public void delete_ManagementPlanning(int id);
    public Department findById_ManagementPlanning(int id);
    public Department findByName_ManagementPlanning(String name);
    public void update_ManagementPlanning(@Param("department") Department department);
}
