package com.example.bunsanedthinking_springback.entity.counsel;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.vo.CounselVO;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:41
 */
@NoArgsConstructor
@Data
public class Counsel implements Cloneable {

	public static int COUNSEL_SERIAL_NUMBER = 520;

	private Date counselDate;
	private int customerID;
	private int id;
	private CounselProcessStatus processStatus;
	private int productID;
	// Customer쪽 정보인듯
	private String name;
	private String phoneNumber;
	private String job;
	private int age;
	private Gender gender;

	public Counsel(int customerId, int productId, String name, String phoneNumber, Date counselDate, String job,
		int age, Gender gender) {
		this.processStatus = CounselProcessStatus.Unprocessed;
		this.customerID = customerId;
		this.productID = productId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.counselDate = counselDate;
		this.job = job;
		this.age = age;
		this.gender = gender;
	}

	public Counsel(CounselVO counselVO, String name, String phoneNumber, String job, int age, Gender gender) {
		LocalDate localDate = counselVO.getCounsel_date();
		counselDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		customerID = counselVO.getCustomer_id();
		id = counselVO.getId();
		processStatus = CounselProcessStatus.values()[counselVO.getProcess_status()];
		productID = counselVO.getProduct_id();

		this.name = name;
		this.phoneNumber = phoneNumber;
		this.job = job;
		this.age = age;
		this.gender = gender;
	}

	public CounselVO findVO() {
		LocalDate lCounselDate = new java.util.Date(counselDate.getTime()).toInstant()
			.atZone(ZoneId.systemDefault())
			.toLocalDate();
		return new CounselVO(id, lCounselDate,
			processStatus.ordinal(), customerID,
			productID);
	}

	public Counsel clone() {
		Counsel counsel = new Counsel(getCustomerID(), getProductID(), getName(), getPhoneNumber(), this.counselDate,
			getJob(), getAge(), getGender());
		counsel.setProcessStatus(getProcessStatus());
		counsel.setId(getId());
		return counsel;
	}

}
