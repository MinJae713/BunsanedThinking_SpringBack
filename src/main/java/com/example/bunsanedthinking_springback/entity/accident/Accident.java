package com.example.bunsanedthinking_springback.entity.accident;

import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.vo.AccidentVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:39
 */
//05.31 ����
@AllArgsConstructor
@Data
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
	public Accident(AccidentVO accidentVO, String customerName, String customerPhoneNumber) {
		id = accidentVO.getId();
		customerID = accidentVO.getCustomer_id();
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		LocalDate localDate = accidentVO.getDate();
		date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		location = accidentVO.getLocation();
		processStatus = AccidentProcessStatus.values()[accidentVO.getProcess_status()];
		serviceType = ServiceType.values()[accidentVO.getService_type()];
	}

	public AccidentVO findVO() {
		LocalDate localDate = new java.util.Date(date.getTime()).toInstant().
				atZone(ZoneId.systemDefault()).
				toLocalDate();
		return new AccidentVO(id, localDate,
				location, processStatus.ordinal(),
				serviceType.ordinal(), customerID);
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

	public String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(this.date);
	}

}
