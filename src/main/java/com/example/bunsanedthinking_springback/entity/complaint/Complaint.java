package com.example.bunsanedthinking_springback.entity.complaint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
//0604 ����
@AllArgsConstructor
public class Complaint {

	public static final int COMPLAINT_SERIAL = 510;
	
	private ComplaintType complaintType;
	private String content;
	private int customerID;
	private String employeeName;
	private int id;
	private Date postDate;
	private Date processingDate;
	private ComplaintProcessStatus processStatus;
	private String title;
	private String result;

	public Complaint(ComplaintType complaintType, String content, int customerId, String title){
		this.processStatus = ComplaintProcessStatus.Unprocessed;
		this.postDate = new Date();
		this.employeeName = null;
		this.processingDate = null;
		this.result = null;
		this.complaintType = complaintType;
		this.content = content;
		this.customerID = customerId;
		this.title = title;
	}
	
	public Complaint(Complaint complaint) {
		this.complaintType = complaint.getComplaintType();
		this.content = complaint.getContent();
		this.customerID = complaint.getCustomerID();
		this.employeeName = complaint.getEmployeeName();
		this.id = complaint.getId();
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = inputFormat.parse(complaint.getPostDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
		this.postDate = date;
		this.processingDate = complaint.getProcessingDate();
		this.processStatus = complaint.getProcessStatus();
		this.title = complaint.getTitle();
	}
	
	public Complaint clone() {
		return new Complaint(this);
	}

	public void handle(String employeeName, String result){
		this.employeeName = employeeName;
		this.result = result;
		this.processingDate = new Date();
		this.processStatus = ComplaintProcessStatus.Completed;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}

	public ComplaintType getComplaintType() {
		return complaintType;
	}

	public void setComplaintType(ComplaintType complaintType) {
		this.complaintType = complaintType;
	}

	public String getContent() {
		return content;
	}

	public int getCustomerID() {
		return customerID;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return this.result;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPostDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.postDate);
	}

	public Date getProcessingDate() {
		return processingDate;
	}

	public void setProcessingDate(Date processingDate) {
		this.processingDate = processingDate;
	}

	public ComplaintProcessStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(ComplaintProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public String getTitle() {
		return title;
	}
}
