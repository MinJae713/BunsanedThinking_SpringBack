package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
	ProductVO get_SalesModel(int id);

    public Optional<ProductVO> getProductById_Customer(int id);
	void delete_ProductManagementModel(int id);

	void updateName_ProductManagementModel(ProductVO productVO);
	void insert_LoanManagement(ProductVO productVO);

	List<ProductVO> getAll_LoanManagement();

	Optional<ProductVO> findById_LoanManagement(int id);

	void update_LoanManagement(ProductVO productVO);

	void delete_LoanManagement(int id);

	Integer isExistName(String name);
	ProductVO getById_ProductManagementModel(int productId);
}
