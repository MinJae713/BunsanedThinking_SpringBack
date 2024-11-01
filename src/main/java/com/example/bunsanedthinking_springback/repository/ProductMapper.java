package com.example.bunsanedthinking_springback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.ProductVO;

@Mapper
public interface ProductMapper {
	ProductVO get_SalesModel(int id);

	void insert_LoanManagement(@Param("product") ProductVO productVO);

	List<Product> getAll_LoanManagement();

	void delete_ProductManagementModel(int id);
}
