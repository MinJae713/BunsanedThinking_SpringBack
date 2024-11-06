package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    public void insert_CustomerInformationManagement(@Param("customer") CustomerVO customerVO);
    public void delete_CustomerInformationManagement(int id);
    public CustomerVO findById_CustomerInformationManagement(int id);
    public CustomerVO findByResidentRegistrationNumber_CustomerInformationManagement(String residentRegistrationNumber);
    public void update_CustomerInformationManagement(@Param("customer") CustomerVO customerVO);
    public List<CustomerVO> getAll_CustomerInformationManagement();
}
