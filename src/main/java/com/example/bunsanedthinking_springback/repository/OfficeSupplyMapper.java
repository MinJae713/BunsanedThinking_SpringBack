package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OfficeSupplyMapper {
    public Optional<OfficeSupplyVO> getById(int id);
    public List<OfficeSupplyVO> getAll();
    public Integer getMaxId();
    public void insert(OfficeSupplyVO officeSupplyVO);
    public void update(OfficeSupplyVO officeSupplyVO);
    public void deleteById(int id);
    public int getTotalInventory();
}
