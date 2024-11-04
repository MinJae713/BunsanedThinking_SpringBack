package com.example.bunsanedthinking_springback.repository;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerMapper {
    public void insert_CustomerInformationManagement(@Param("customer") Customer customer);
    public void delete_CustomerInformationManagement(int id);
    public Customer findById_CustomerInformationManagement(int id);
    public Customer findByResidentRegistrationNumber_CustomerInformationManagement(String residentRegistrationNumber);
    public void update_CustomerInformationManagement(@Param("customer") Customer customer);
    public List<Customer> getAll_CustomerInformationManagement();
}
