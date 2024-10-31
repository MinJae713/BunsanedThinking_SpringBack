package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    // 아래는 예시
    public void delete_managementPlanning(int id);
    public void delete_humanResource(int id);
}
