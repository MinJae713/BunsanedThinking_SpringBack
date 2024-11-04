package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CompensationDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompensationDetailMapper {
    public List<CompensationDetailVO> getAllCompensationByContractId_Customer(int id);
}
