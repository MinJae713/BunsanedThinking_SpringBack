package com.example.bunsanedthinking_springback.entity.complaint;

import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.vo.ComplaintVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
//0604 ����
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Complaint {
	
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

	public ComplaintVO findVO() {
		LocalDate lPostDate = postDate == null ? null :
				new java.util.Date(postDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate lProcessingDate = processingDate == null ? null :
				new java.util.Date(processingDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new ComplaintVO(id, complaintType.ordinal(),
				content, lPostDate, employeeName,
				lProcessingDate, processStatus.ordinal(),
				result, title, customerID);
	}

	public Complaint(ComplaintVO complaintVO) {
		id = complaintVO.getId();
		complaintType = ComplaintType.values()[complaintVO.getComplaint_type()];
		content = complaintVO.getContent();
		customerID = complaintVO.getCustomer_id();
		employeeName = complaintVO.getEmployee_name();
		postDate = java.sql.Date.valueOf(complaintVO.getDate());
		processingDate = java.sql.Date.valueOf(complaintVO.getProcessing_date());
		processStatus = ComplaintProcessStatus.values()[complaintVO.getProcess_status()];
		title = complaintVO.getTitle();
		result = complaintVO.getResult();
	}
	
	public Complaint(Complaint complaint) {
		this.complaintType = complaint.getComplaintType();
		this.content = complaint.getContent();
		this.customerID = complaint.getCustomerID();
		this.employeeName = complaint.getEmployeeName();
		this.id = complaint.getId();
		SimpleDateFormat inputFormat = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
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

	public String getPostDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.DATE_FORMAT);
        return dateFormat.format(this.postDate);
	}

}
