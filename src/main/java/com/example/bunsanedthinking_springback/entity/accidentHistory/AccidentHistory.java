package com.example.bunsanedthinking_springback.entity.accidentHistory;

import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */

//2024-06-04 김대현
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

	public AccidentHistory(AccidentHistoryVO accidentHistoryVO) {
		customerID = accidentHistoryVO.getCustomer_id();
		LocalDate localDate = accidentHistoryVO.getDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		date = new Date(year, month, day);
		accidentDetail = accidentHistoryVO.getDetails_of_accident();
		id = accidentHistoryVO.getId();
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

	public String getAccidentDetail() {
		return accidentDetail;
	}

	public void setAccidentDetail(String accidentDetail) {
		this.accidentDetail = accidentDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccidentHistory clone() {
		AccidentHistory accidentHistory = new AccidentHistory(getAccidentDetail(), this.date);
		accidentHistory.setId(getId());
		accidentHistory.setCustomerID(getCustomerID());
		return accidentHistory;
	}
	
}