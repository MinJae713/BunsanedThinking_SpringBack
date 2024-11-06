package com.example.bunsanedthinking_springback.model.domain.customer;

import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerDModel {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ContractMapper contractMapper;
    public Customer getById(int id) {
        Customer customer = new Customer();
        customerMapper.getById_Customer(id);
        contractMapper.getById_Customer(id);
        return customer;
    }
    public List<Customer> getAll() {
        return new ArrayList<Customer>();
    }
    public int getMaxId() {
        return customerMapper.getMaxId_SalesModel();
    }
    public void update(CustomerVO customerVO) {

    }
    public void insert(CustomerVO customerVO) {

    }
    public void delete(int id) {

    }
}
