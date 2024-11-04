package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.ServiceVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.ArrayList;

@Mapper
public interface ServiceMapper {
    public List<ServiceVO> getAllByProductId_Customer(int id);
	ArrayList<ServiceVO> get_SalesModel(int id);
	void insert_ProductManagement(ServiceVO serviceVO);
	void delete_ProductManagementModel(int id);
}
