package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OfficeSupplyMapper {
    public void insert(OfficeSupplyVO officeSupplyVO);
    public void delete(int id);
    public Optional<OfficeSupplyVO> getById(int id);
    public List<OfficeSupplyVO> getAll();
    public int getTotalInventory();
    public Integer getMaxId();
    public void update(OfficeSupplyVO officeSupplyVO);
}
