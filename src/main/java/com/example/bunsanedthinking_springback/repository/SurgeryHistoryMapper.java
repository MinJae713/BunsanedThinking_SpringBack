package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SurgeryHistoryMapper {
    public Optional<SurgeryHistoryVO> getById(int id);
    public List<SurgeryHistoryVO> getAll();
    public Integer getMaxId();
    public void insert(SurgeryHistoryVO surgeryHistoryVO);
    public void update(SurgeryHistoryVO surgeryHistoryVO);
    public void deleteById(int id);
}
