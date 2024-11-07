package com.example.bunsanedthinking_springback.model.domain.employee;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.family.Family;
import com.example.bunsanedthinking_springback.entity.paymentDetail.PaymentDetail;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.model.domain.family.FamilyDModel;
import com.example.bunsanedthinking_springback.model.domain.paymentDetail.PaymentDetailDModel;
import com.example.bunsanedthinking_springback.repository.EmployeeMapper;
import com.example.bunsanedthinking_springback.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDModel {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private FamilyDModel familyDModel;
    @Autowired
    private PaymentDetailDModel paymentDetailDModel;
    @Autowired
    private ContractDModel contractDModel;

    public Employee getById(int id) {
        EmployeeVO employeeVO = employeeMapper.findById_HumanResource(id).orElse(null);
        if (employeeVO == null) return null;
        ArrayList<Family> families = new ArrayList<Family>();
        ArrayList<PaymentDetail> paymentDetails = new ArrayList<PaymentDetail>();
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        familyDModel.getAll().stream().filter(e -> e.getEmployeeID() == id).forEach(e -> families.add(e));
        paymentDetailDModel.getAll().stream().filter(e -> e.getEmployeeId() == id).forEach(e -> paymentDetails.add(e));
        contractDModel.getAll().stream().filter(e -> e.getEmployeeID() == id).forEach(e -> contracts.add(e));
        return employeeVO.getEntity(families, paymentDetails, contracts);
    }
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<Employee>();
        employeeMapper.getAll_HumanResource().stream().forEach(e -> employees.add(getById(e.getId())));
        return employees;
    }
    public int getMaxId() {
        return employeeMapper.getMaxId();
    }
    public void add(EmployeeVO employeeVO) {
        employeeMapper.insert_HumanResource(employeeVO);
    }
    public void update(EmployeeVO employeeVO) {
        employeeMapper.update_HumanResource(employeeVO);
    }
    public void delete(int id) {
        employeeMapper.delete_HumanResource(id);
    }
}
