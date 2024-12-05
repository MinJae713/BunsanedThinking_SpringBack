package com.example.bunsanedthinking_springback.entity.surgeryHistory;

import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;
import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = CommonConstants.DATE_FORMAT)
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

	public SurgeryHistory clone() {
		SurgeryHistory surgeryHistory =new SurgeryHistory(getName(), getHospitalName(), this.date);
		surgeryHistory.setId(getId());
		surgeryHistory.setCustomerID(getCustomerID());
		return surgeryHistory;
		
	}

}