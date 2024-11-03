package com.example.bunsanedthinking_springback.entity.accident;

import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:39
 */
//05.31 ����
@AllArgsConstructor
public class Accident {

	public static final int ACCIDENT_SERIAL = 400;
	
	private int customerID;
	private String customerName;
	private String customerPhoneNumber;
	private Date date;
	private int id;
	private String location;
	private AccidentProcessStatus processStatus;
	private ServiceType serviceType;

	public Accident() {
		
	}

	public void report(int customerID, String customerName, String customerPhoneNumber, Date date, String location,
			ServiceType serviceType){
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.date = date;
		this.location = location;
		this.serviceType = serviceType;
		this.processStatus = AccidentProcessStatus.Unprocessed;
	}
	
	public void processing() {
		this.processStatus = AccidentProcessStatus.Processing;
	}

	public void complete() {
		this.processStatus = AccidentProcessStatus.Completed;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}
	
	public ServiceType getServiceType() {
		return this.serviceType;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public AccidentProcessStatus getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(AccidentProcessStatus processStatus) {
		this.processStatus = processStatus;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

}
