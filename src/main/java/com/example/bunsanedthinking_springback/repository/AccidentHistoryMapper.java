package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AccidentHistoryMapper {
    public Optional<AccidentHistoryVO> getById(int id);
    public List<AccidentHistoryVO> getAll();
    public void insert(AccidentHistoryVO accidentHistoryVO);
    public void update(AccidentHistoryVO accidentHistoryVO);
    public void deleteById(int id);
    public Integer getMaxId();
}
