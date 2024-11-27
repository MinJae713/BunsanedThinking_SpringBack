package com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.handleComplaint;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.global.util.DateUtils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HandleComplaintResponse {
	private String complaintType;
	private String customerName;
	private String customerPhoneNumber;
	private String employeeName;
	private int id;
	private String postDate;
	private String processingDate;
	private String processStatus;
	private String title;

	public static HandleComplaintResponse of(Complaint complaint, Customer customer) {
		return new HandleComplaintResponse(complaint.getComplaintType().getName(), customer.getName(),
			customer.getPhoneNumber(), complaint.getEmployeeName(), complaint.getId(),
			complaint.getPostDate(), DateUtils.toString(complaint.getProcessingDate()),
			complaint.getProcessStatus().getText(), complaint.getTitle());
	}

	public static HandleComplaintResponse ofWithDetail(Complaint complaint, Customer customer) {
		return HandleComplaintDetailResponse.of(complaint, customer);
	}
}
