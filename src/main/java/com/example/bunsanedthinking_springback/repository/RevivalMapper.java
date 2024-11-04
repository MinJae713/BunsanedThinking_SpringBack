package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.RevivalVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RevivalMapper {
    public Optional<RevivalVO> getById_Customer(int id);
    public void addById_Customer(int contractId);

    public Optional<RevivalVO> getById_ContractManagement(int id);
    public List<RevivalVO> getAll_ContractManagement();
}
