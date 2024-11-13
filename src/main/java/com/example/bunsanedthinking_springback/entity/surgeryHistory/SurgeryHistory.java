package com.example.bunsanedthinking_springback.entity.surgeryHistory;

import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:44
 */
//2024-06-04 김대현
@Data
@NoArgsConstructor
public class SurgeryHistory implements Cloneable {
	
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

	public SurgeryHistory clone() {
		SurgeryHistory surgeryHistory =new SurgeryHistory(getName(), getHospitalName(), this.date);
		surgeryHistory.setId(getId());
		surgeryHistory.setCustomerID(getCustomerID());
		return surgeryHistory;
		
	}

}