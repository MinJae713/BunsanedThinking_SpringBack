package com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.handleComplaint;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import lombok.Getter;

import java.util.Date;

@Getter
public class HandleComplaintDetailResponse extends HandleComplaintResponse {
	private String content;

	public HandleComplaintDetailResponse(String complaintType, String customerName, String customerPhoneNumber,
		String employeeName, int id, String postDate, Date processingDate, String processStatus,
		String title, String content) {
		super(complaintType, customerName, customerPhoneNumber, employeeName, id, postDate,
			DateUtils.toString(processingDate), processStatus, title);
		this.content = content;
	}

	public static HandleComplaintDetailResponse of(Complaint complaint, Customer customer) {
		return new HandleComplaintDetailResponse(complaint.getComplaintType().getName(), customer.getName(),
				customer.getPhoneNumber(), complaint.getEmployeeName(), complaint.getId(),
				complaint.getPostDate(), complaint.getProcessingDate(),
				complaint.getProcessStatus().getText(), complaint.getTitle(), complaint.getContent());
	}
}
