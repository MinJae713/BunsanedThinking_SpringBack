package com.example.bunsanedthinking_springback.dto.employee.customerSupport.response;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.customer.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetComplaintResponse {
	Complaint complaint;
	Customer customer;
}
