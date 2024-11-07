package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;

@Mapper
public interface PaymentDetailMapper {
	public int getCount_Compensation();

	public Integer getLastId_Compensation();

	public void add_Compensation(PaymentDetailVO paymentDetailVO);

	void insert_LoanManagement(PaymentDetailVO paymentDetailVO);

	Integer getMaxId_LoanManagement();

	void update_FinancialAccountant(PaymentDetailVO paymentDetailVO);

	List<PaymentDetailVO> getAll_FinancialAccountant();

	List<PaymentDetailVO> findByProcessStatus_FinancialAccountant(int status);

	Optional<PaymentDetailVO> findById_FinancialAccountant(int id);

	List<PaymentDetailVO> findByEmployeeId_HumanResource(int id);

	public void deleteById(int id);
}
