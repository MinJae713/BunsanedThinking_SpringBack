package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ComplaintVO;

@Mapper
public interface ComplaintMapper {
	Optional<ComplaintVO> findById_CustomerSupport(int id);

	void update_CustomerSupport(ComplaintVO complaintVO);

	List<ComplaintVO> getAll_CustomerSupport();

	List<ComplaintVO> findByProcessStatus_CustomerSupport(int processStatus);
}
