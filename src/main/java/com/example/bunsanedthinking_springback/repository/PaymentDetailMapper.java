package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.PaymentDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentDetailMapper {
	public Optional<PaymentDetailVO> getById(int id);
	public List<PaymentDetailVO> getAll();
	public Integer getMaxId();
	public void insert(PaymentDetailVO paymentDetailVO);
	public void update(PaymentDetailVO paymentDetailVO);
	public void deleteById(int id);
}
