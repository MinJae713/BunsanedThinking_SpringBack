package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;

@Mapper
public interface SurgeryHistoryMapper {
	void insert(SurgeryHistoryVO surgeryHistoryVO);
	Integer getMaxId();
    Optional<SurgeryHistoryVO> getById(int id);
    List<SurgeryHistoryVO> getAll();
    void update(SurgeryHistoryVO surgeryHistoryVO);
    void deleteById(int id);
}
