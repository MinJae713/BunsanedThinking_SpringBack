package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SurgeryHistoryMapper {
    public List<SurgeryHistoryVO> getAllByCustomerId_Customer(int id);
}
