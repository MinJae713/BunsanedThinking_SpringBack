package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InsuranceMapper {
    public Optional<InsuranceVO> getInsuranceById_Customer(int id);
    public List<InsuranceVO> getAll_Customer();
}
