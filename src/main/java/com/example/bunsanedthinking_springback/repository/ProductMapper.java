package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
	ProductVO get_SalesModel(int id);

	void insert_LoanManagement(@Param("product") ProductVO productVO);

	List<Product> getAll_LoanManagement();
    public Optional<ProductVO> getProductById_Customer(int id);
	void delete_ProductManagementModel(int id);

	void update_ProductManagementModel(ProductVO productVO);
	void insert_LoanManagement(ProductVO productVO);

	List<ProductVO> getAll_LoanManagement();

	Optional<ProductVO> findById_LoanManagement(int id);

	void update_LoanManagement(ProductVO productVO);

	void delete_LoanManagement(int id);
}
