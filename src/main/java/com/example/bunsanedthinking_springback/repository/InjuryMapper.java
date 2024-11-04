package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InjuryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InjuryMapper {
    public Optional<InjuryVO> getInjuryById_Customer(int id);
    public List<InjuryVO> getAll_Customer();
}
