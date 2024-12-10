package com.example.bunsanedthinking_springback.model.entityModel.sales;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.employee.EmployeeEntityModel;
import com.example.bunsanedthinking_springback.repository.SalesMapper;
import com.example.bunsanedthinking_springback.vo.SalesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalesEntityModel {
	@Autowired
	private EmployeeEntityModel employeeEntityModel;
	@Autowired
	private SalesMapper salesMapper;
	@ReadOnly
	public Sales getById(int id) {
		SalesVO salesVO = salesMapper.getById(id).orElse(null);
		if (salesVO == null)
			return null;
		Employee employee = employeeEntityModel.getById(id);
		return salesVO.getEntity(employee);
	}
	@ReadOnly
	public List<Sales> getAll() {
		List<Sales> sales = new ArrayList<Sales>();
		salesMapper.getAll().forEach(e -> sales.add(getById(e.getEmployee_id())));
		return sales;
	}
	@ReadOnly
	public Integer getMaxId() {
		return salesMapper.getMaxId();
	}

	public void add(Sales sales) {
		if (sales == null) return;
		if (salesMapper.getById(sales.getId()).isPresent()) return;
		employeeEntityModel.add(sales);
		salesMapper.insert(sales.findVO());
	}

	public void update(Sales sales) {
		if (sales == null) return;
		if (salesMapper.getById(sales.getId()).isEmpty()) return;
		salesMapper.update(sales.findVO());
		employeeEntityModel.update(sales);
	}

	public void delete(int id) {
		if (salesMapper.getById(id).isEmpty()) return;
		salesMapper.deleteById(id);
		employeeEntityModel.delete(id);
	}
}
