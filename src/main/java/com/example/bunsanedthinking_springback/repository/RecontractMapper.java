package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.RecontractVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RecontractMapper {
    public Optional<RecontractVO> getById_Customer(int id);
    public void addById_Customer(int contractId);

    public Optional<RecontractVO> getById_ContractManagement(int id);
    public List<RecontractVO> getAll_ContractManagement();

    void updateStatus_ContractManagement(int status, int id);

    public int getMaxId();
    public void insert(RecontractVO recontractVO);
    public void update(RecontractVO recontractVO);
    public void deleteById(int id);
}
