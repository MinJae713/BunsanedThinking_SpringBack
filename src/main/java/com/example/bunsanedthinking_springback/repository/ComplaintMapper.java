package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ComplaintVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ComplaintMapper {
    public Optional<ComplaintVO> getComplaintById_Customer(int id);
    public List<ComplaintVO> getComplaintByCustomerId_Customer(int id);
	Optional<ComplaintVO> findById_CustomerSupport(int id);

	void update_CustomerSupport(ComplaintVO complaintVO);

	List<ComplaintVO> getAll_CustomerSupport();
	List<ComplaintVO> findByProcessStatus_CustomerSupport(int processStatus);
	public int getMaxId();
	public void insert(ComplaintVO complaintVO);
	public void deleteById(int id);
}
