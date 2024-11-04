package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ContractVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ContractMapper {
    public Optional<ContractVO> getById_Customer(int id);
    public Optional<ContractVO> getAllByProductId_Customer(int id);
    public List<ContractVO> getAllByCustomerId_Customer(int id);
    public List<ContractVO> getAll_Customer();

    public void updateStatus_paymentDate_Customer(int m_contractStatus, LocalDate m_paymentDate, int m_contractId);

    public List<ContractVO> getAllByCustomerId_Compensation(int id);
}
