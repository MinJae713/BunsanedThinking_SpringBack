package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesVO {
	private int employee_id;
	private int evaluate;
	private int contract_count;

	public Sales getEntity(Employee employee) {
		// 여기 지정이 쫌 애매하네
		Sales sales = new Sales(employee, evaluate, contract_count);
		return sales;
	}
}
