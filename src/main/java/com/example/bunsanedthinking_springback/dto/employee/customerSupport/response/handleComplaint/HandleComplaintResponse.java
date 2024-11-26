package com.example.bunsanedthinking_springback.dto.employee.customerSupport.response.handleComplaint;

import java.text.ParseException;
import java.util.Date;

import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.global.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date postDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date processingDate;
	private String processStatus;
	private String title;

	public static HandleComplaintResponse of(Complaint complaint, Customer customer) {
		try {
			return new HandleComplaintResponse(complaint.getComplaintType().getName(), customer.getName(),
				customer.getPhoneNumber(), complaint.getEmployeeName(), complaint.getId(),
				DateUtils.toDate(complaint.getPostDate()), complaint.getProcessingDate(),
				complaint.getProcessStatus().getText(), complaint.getTitle());
		} catch (ParseException e) {
			return null;
		}
	}

	public static HandleComplaintResponse ofWithDetail(Complaint complaint, Customer customer) {
		return HandleComplaintDetailResponse.of(complaint, customer);
	}
}
