package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.AdditionalAllowanceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AdditionalAllowanceMapper {
    public Optional<AdditionalAllowanceVO> getById(int id);
    public List<AdditionalAllowanceVO> getAll();
    public int getMaxId();
    public void insert(AdditionalAllowanceVO additionalAllowanceVO);
    public void update(AdditionalAllowanceVO additionalAllowanceVO);
    public void deleteById(int id);
}
