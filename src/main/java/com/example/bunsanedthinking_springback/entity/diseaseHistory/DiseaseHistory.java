package com.example.bunsanedthinking_springback.entity.diseaseHistory;

import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
//2024-06-04 김대현
@NoArgsConstructor
public class DiseaseHistory implements Cloneable{
	public static final int DISEASE_HISTORY_SERIAL_NUMBER = 510;

//	diagnosisDate -> date_of_diagnosis
//	customerID -> customer_id
	private int customer_id;
	private Date date_of_diagnosis;
	private int id;
	private String name;

	public DiseaseHistory(String name, Date date) {
		this.setName(name);
		this.setDate_of_diagnosis(date);
	}
	public DiseaseHistory(DiseaseHistoryVO diseaseHistoryVO) {
		customer_id = diseaseHistoryVO.getCustomer_id();
		LocalDate localDate = diseaseHistoryVO.getDate_of_diagnosis();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		date_of_diagnosis = new Date(year, month, day);
		id = diseaseHistoryVO.getId();
		name = diseaseHistoryVO.getName();
	}
	public DiseaseHistoryVO findVO() {
		LocalDate lDate = LocalDate.of(date_of_diagnosis.getYear(), date_of_diagnosis.getMonth()+1, date_of_diagnosis.getDay());
		return new DiseaseHistoryVO(id, lDate, name, customer_id);
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getDate_of_diagnosis() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date_of_diagnosis);
	}

	public void setDate_of_diagnosis(Date date_of_diagnosis) {
		this.date_of_diagnosis = date_of_diagnosis;
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
	public DiseaseHistory clone(){
		DiseaseHistory diseaseHistory = new DiseaseHistory(getName(), this.date_of_diagnosis);
		diseaseHistory.setId(getId());
		diseaseHistory.setCustomer_id(getCustomer_id());
		return diseaseHistory;
		
	}
	@Override
	public String toString() {
		return "DiseaseHistory{" +
				"customerID=" + customer_id +
				", diagnosisDate=" + date_of_diagnosis +
				", id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	public DiseaseHistoryVO finddiseaseVO() {
		DiseaseHistoryVO result = new DiseaseHistoryVO();
		result.setId(id);
		result.setName(name);
		result.setCustomer_id(customer_id);
		LocalDate localDate = LocalDate.of(date_of_diagnosis.getYear(), date_of_diagnosis.getMonth()+1, date_of_diagnosis.getDay());
		result.setDate_of_diagnosis(localDate);

		return result;
	}

//	뭐가 맞는지모르겠다
//public DiseaseHistoryVO findVO() {
//	if (date_of_diagnosis == null) {
//		throw new IllegalArgumentException("Date of diagnosis is null and cannot be converted to LocalDate.");
//	}
//	System.out.println("Converting date_of_diagnosis: " + date_of_diagnosis); // 디버깅용 로그 추가
//	// Date 객체를 안전하게 LocalDate로 변환
//	LocalDate lDate = date_of_diagnosis.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	return new DiseaseHistoryVO(id, lDate, name, customer_id);
//}
//
//	public DiseaseHistoryVO finddiseaseVO() {
//		if (date_of_diagnosis == null) {
//			throw new IllegalArgumentException("Date of diagnosis is null and cannot be converted to LocalDate.");
//		}
//		System.out.println("Converting date_of_diagnosis in finddiseaseVO: " + date_of_diagnosis); // 디버깅용 로그 추가
//
//		DiseaseHistoryVO result = new DiseaseHistoryVO();
//		result.setId(id);
//		result.setName(name);
//		result.setCustomer_id(customer_id);
//		// Date 객체를 안전하게 LocalDate로 변환
//		LocalDate localDate = date_of_diagnosis.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		result.setDate_of_diagnosis(localDate);
//
//		return result;
//	}


}