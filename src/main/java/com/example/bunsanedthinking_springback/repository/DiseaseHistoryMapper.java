package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiseaseHistoryMapper {
    public List<DiseaseHistoryVO> getAllByCustomerId_Customer(int id);
}
