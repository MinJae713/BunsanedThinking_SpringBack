package com.example.bunsanedthinking_springback.entity.surgeryHistory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
//2024-06-04 김대현
public class SurgeryHistory implements Cloneable {

	public static final int SURGERYHISTORY_SERIAL_NUMBER = 610;
	
	private int customerID;
	private Date date;
	private String hospitalName;
	private int id;
	private String name;

	public SurgeryHistory(String name, String hospitalName, Date date){
		this.setName(name);
		this.setHospitalName(hospitalName);
		this.setDate(date);
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SurgeryHistory clone() {
		SurgeryHistory surgeryHistory =new SurgeryHistory(getName(), getHospitalName(), this.date);
		surgeryHistory.setId(getId());
		surgeryHistory.setCustomerID(getCustomerID());
		return surgeryHistory;
		
	}
}