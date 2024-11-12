package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;

@Mapper
public interface PaymentDetailMapper {

	Integer getMaxId();

	void insert(PaymentDetailVO paymentDetailVO);

	void update(PaymentDetailVO paymentDetailVO);

	List<PaymentDetailVO> getAll();

	Optional<PaymentDetailVO> getById(int id);

	void deleteById(int id);
}
