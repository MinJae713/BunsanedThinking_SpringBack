package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CustomerMapper {
    public List<CustomerVO> getAll_Customer();
    public Optional<CustomerVO> getById_Customer(int id);
    public Optional<String> getNameById_Customer(int id);
    public Optional<String> getPNById_Customer(int id);

    public Optional<CustomerVO> getById_Compensation(int id);
    CustomerVO get_UnderWritingModel(int id);

	void insert_SalesModel(CustomerVO customerVO);

	CustomerVO get_SalesModel(int id);
	Optional<CustomerVO> findById_FinancialAccountant(int id);
}
