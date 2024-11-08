package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurgeryHistoryVO {
	private int id;
	private String hospital_name;
	private String name;
	private LocalDate date;
	private int customer_id;

	public SurgeryHistory getEntity() {
		SurgeryHistory surgeryHistory = new SurgeryHistory();
		surgeryHistory.setId(id);
		surgeryHistory.setDate(Date.valueOf(date));
		surgeryHistory.setCustomerID(customer_id);
		surgeryHistory.setHospitalName(hospital_name);
		surgeryHistory.setName(name);
		return surgeryHistory;
	}
}
