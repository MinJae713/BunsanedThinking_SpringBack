package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
	public Optional<ProductVO> getById(int id);
	public List<ProductVO> getAll();
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void deleteById(int id);

}
