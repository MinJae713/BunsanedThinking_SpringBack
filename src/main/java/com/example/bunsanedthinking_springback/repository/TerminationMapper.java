package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.TerminationVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface TerminationMapper {
    public Optional<TerminationVO> getById_Customer(int id);
    public void addById_Customer(int contractId);

    public Optional<TerminationVO> getById_ContractManagement(int id);
    public List<TerminationVO> getAll_ContractManagement();

    void updateStatus_ContractManagement(int status, int contract_id);
    void updateApplyDate_ContractManagement(LocalDateTime apply_date, int contract_id);

    public int getMaxId();
    public void insert(TerminationVO terminationVO);
    public void update(TerminationVO terminationVO);
    public void deleteById(int id);
}
