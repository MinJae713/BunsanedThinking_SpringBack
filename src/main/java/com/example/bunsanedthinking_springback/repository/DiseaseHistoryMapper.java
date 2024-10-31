package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiseaseHistoryMapper {
    public List<DiseaseHistory> getTst();
    // 테스트
}
