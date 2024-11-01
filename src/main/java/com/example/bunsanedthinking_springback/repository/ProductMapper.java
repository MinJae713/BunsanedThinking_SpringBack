package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.ProductVO;

@Mapper
public interface ProductMapper {
	void insert_LoanManagement(ProductVO productVO);

	List<ProductVO> getAll_LoanManagement();

	Optional<ProductVO> findById_LoanManagement(int id);

	void update_LoanManagement(ProductVO productVO);

	void delete_LoanManagement(int id);
}
