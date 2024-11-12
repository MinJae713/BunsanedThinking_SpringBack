package com.example.bunsanedthinking_springback.entity.accidentHistory;

import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */

//2024-06-04 김대현
@NoArgsConstructor
@Data
public class AccidentHistory implements Cloneable{
	public static final int ACCIDENT_HISTORY_SERIAL_NUMBER = 410;
	
	private int customerID;
	private Date date;
	private String accidentDetail;
	private int id;

	public AccidentHistory(String detailsOfAccident, Date date){
		this.setAccidentDetail(detailsOfAccident);
		this.setDate(date);
	}

	public AccidentHistoryVO findVO() {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return new AccidentHistoryVO(id,
				localDate, accidentDetail, customerID);
	}

	public AccidentHistory(AccidentHistoryVO accidentHistoryVO) {
		customerID = accidentHistoryVO.getCustomer_id();
		LocalDate localDate = accidentHistoryVO.getDate();
		date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		accidentDetail = accidentHistoryVO.getDetails_of_accident();
		id = accidentHistoryVO.getId();
	}

	public AccidentHistory clone() {
		AccidentHistory accidentHistory = new AccidentHistory(getAccidentDetail(), this.date);
		accidentHistory.setId(getId());
		accidentHistory.setCustomerID(getCustomerID());
		return accidentHistory;
	}

}