package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CounselVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CounselMapper {
    public List<CounselVO> getAllByCustomerId_Customer(int id);
}
