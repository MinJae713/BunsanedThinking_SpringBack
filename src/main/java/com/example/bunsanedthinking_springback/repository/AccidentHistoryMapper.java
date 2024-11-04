package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccidentHistoryMapper {
    public List<AccidentHistoryVO> getAllByCustomerId_Customer(int id);

    public List<AccidentHistoryVO> getAllByCustomerId_CustomerSupport(int id);
}
