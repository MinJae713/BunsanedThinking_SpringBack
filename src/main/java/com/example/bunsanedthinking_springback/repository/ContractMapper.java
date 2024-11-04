package com.example.bunsanedthinking_springback.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.vo.ContractVO;

@Mapper
public interface ContractMapper {
	Optional<ContractVO> findById_FinancialAccountant(int id);

	Optional<ContractVO> findByCustomerIdAndProductId_FinancialAccountant(@Param("customer_id")int customerID, @Param("product_id") int productId);
}
