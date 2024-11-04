package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.officeSupply.OfficeSupply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfficeSupplyMapper {
    public void insert_OfficeSupply(@Param("officeSupply") OfficeSupply officeSupply);
    public void delete_OfficeSupply(int id);
    public OfficeSupply findById_OfficeSupply(int id);
    public OfficeSupply findByName_OfficeSupply(String name);
    public void update_OfficeSupply(@Param("officeSupply") OfficeSupply officeSupply);
    public List<OfficeSupply> getAll_OfficeSupply();
    public int getTotalInventory_OfficeSupply();
}
