package com.example.bunsanedthinking_springback.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;

@Mapper
public interface PaymentDetailMapper {
	void insert_LoanManagement(PaymentDetailVO paymentDetailVO);

	Integer getMaxId_LoanManagement();
}
