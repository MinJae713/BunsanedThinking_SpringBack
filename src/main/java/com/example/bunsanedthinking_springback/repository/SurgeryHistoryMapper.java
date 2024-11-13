package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;

@Mapper
public interface SurgeryHistoryMapper {
    public Optional<SurgeryHistoryVO> getById(int id);
    public List<SurgeryHistoryVO> getAll();
    public Integer getMaxId();
    public void insert(SurgeryHistoryVO surgeryHistoryVO);
    public void update(SurgeryHistoryVO surgeryHistoryVO);
    public void deleteById(int id);

}
