package com.example.bunsanedthinking_springback.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.bunsanedthinking_springback.vo.ProductVO;

@Mapper
public interface ProductMapper {

	void insert(ProductVO productVO);

    Optional<ProductVO> getById(int id);

	List<ProductVO> getAll();

	void update(ProductVO productVO);

	void deleteById(int id);

}
