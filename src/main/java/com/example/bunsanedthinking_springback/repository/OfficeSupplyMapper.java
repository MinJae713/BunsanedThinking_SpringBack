package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.OfficeSupplyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfficeSupplyMapper {
    public void insert_OfficeSupply(OfficeSupplyVO officeSupplyVO);
    public void delete_OfficeSupply(int id);
    public OfficeSupplyVO findById_OfficeSupply(int id);
    public OfficeSupplyVO findByName_OfficeSupply(@Param("name") String name);
    public void update_OfficeSupply(OfficeSupplyVO officeSupplyVO);
    public List<OfficeSupplyVO> getAll_OfficeSupply();
    public int getTotalInventory_OfficeSupply();

    Integer getMaxId_Administrative();

    public void update(OfficeSupplyVO officeSupplyVO);
}
