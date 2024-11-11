package com.example.bunsanedthinking_springback.entity.surgeryHistory;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
//2024-06-04 김대현
@NoArgsConstructor
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
	public SurgeryHistory(SurgeryHistoryVO surgeryHistoryVO) {
		customerID = surgeryHistoryVO.getCustomer_id();
		LocalDate localDate = surgeryHistoryVO.getDate();
		date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		hospitalName = surgeryHistoryVO.getHospital_name();
		id = surgeryHistoryVO.getId();
		name = surgeryHistoryVO.getName();
	}

	public SurgeryHistoryVO findVO() {
		LocalDate lDate = new java.util.Date(date.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new SurgeryHistoryVO(id, hospitalName,
				name, lDate, customerID);
	}

//	뭐가 맞는지모르겠다
//public SurgeryHistoryVO findVO() {
//	if (date == null) {
//		throw new IllegalArgumentException("Date is null and cannot be converted to LocalDate.");
//	}
//	System.out.println("Converting date: " + date); // 디버깅용 로그 추가
//	// Date 객체를 안전하게 LocalDate로 변환
//	LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	// SurgeryHistoryVO 객체 반환
//	return new SurgeryHistoryVO(id, hospitalName, name, lDate, customerID);
//}



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

	public SurgeryHistoryVO findsurgeryVO() {
		SurgeryHistoryVO result = new SurgeryHistoryVO();
		result.setId(id);
		result.setHospital_name(hospitalName);
		result.setName(name);
		result.setCustomer_id(customerID);
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		result.setDate(localDate);

		return result;
	}
}