package com.example.bunsanedthinking_springback.dto.employee.sales.response;

import com.example.bunsanedthinking_springback.entity.employee.EmployeePosition;
import com.example.bunsanedthinking_springback.entity.employee.Sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluateSalesPerformanceDetailResponse {
	private Integer id;
	private String name;
	private String  position;
	private Integer contractCount;
	private Integer salary;

	public static EvaluateSalesPerformanceDetailResponse from(Sales sales){
		return EvaluateSalesPerformanceDetailResponse.builder().id(sales.getId()).name(sales.getName()).position(sales.getPosition().getName()).contractCount(sales.getContractCount())
			.salary(sales.getSalary()).build();
	}

}