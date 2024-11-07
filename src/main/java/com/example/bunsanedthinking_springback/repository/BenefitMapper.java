package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.BenefitVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BenefitMapper {
    public Optional<BenefitVO> getById(int id);
    public List<BenefitVO> getAll();
    public int getMaxId();
    public void insert(BenefitVO benefitVO);
    public void update(BenefitVO benefitVO);
    public void deleteById(int id);
}
