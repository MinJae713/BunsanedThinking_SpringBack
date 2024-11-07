package com.example.bunsanedthinking_springback.model.domain.sales;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.model.domain.employee.EmployeeDModel;
import com.example.bunsanedthinking_springback.repository.SalesMapper;
import com.example.bunsanedthinking_springback.vo.SalesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesDModel {
    @Autowired
    private EmployeeDModel employeeDModel;
    @Autowired
    private SalesMapper salesMapper;
    public Sales getById(int id) {
        SalesVO salesVO = salesMapper.get_SalesModel(id); // Optional 수정 필요
        if (salesVO == null) return null;
        Employee employee = employeeDModel.getById(id);
        return salesVO.getEntity(employee);
    }
    public List<Sales> getAll() {
        List<Sales> sales = new ArrayList<Sales>();
        salesMapper.getAll_SalesModel().stream().forEach(e -> sales.add(getById(e.getEmployee_id())));
        // getAll_SalesModel - List 수정 필요
        return sales;
    }
    public int getMaxId() {
        return salesMapper.getMaxId();
    }
    public void add(SalesVO salesVO) {
        salesMapper.insert(salesVO);
    }
    public void update(SalesVO salesVO) {
        salesMapper.update_SalesModel(salesVO);
    }
    public void delete(int id) {
        salesMapper.deleteById(id);
    }
}
