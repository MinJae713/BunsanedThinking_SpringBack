package com.example.bunsanedthinking_springback.model.domain.sales;

import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.repository.EmployeeMapper;
import com.example.bunsanedthinking_springback.repository.SalesMapper;
import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import com.example.bunsanedthinking_springback.vo.SalesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesDModel {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SalesMapper salesMapper;
    public Sales getById(int id) {
        SalesVO salesVO = salesMapper.get_SalesModel(id); // Optional 수정 필요
        EmployeeVO employeeVO = employeeMapper.findById_HumanResource(id).orElse(null);
        return salesVO.getEntity(employeeVO); // 여기서 걸림.. 일단 보류 - 엔티티 만드는게 완성이 안됨
    }
    public List<Sales> getAll() {
        List<Sales> sales = new ArrayList<Sales>();
        ArrayList<SalesVO> salesVOS = salesMapper.getAll_SalesModel(); // List 수정 필요
        salesVOS.stream().forEach(e -> sales.add(getById(e.getEmployee_id())));
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
