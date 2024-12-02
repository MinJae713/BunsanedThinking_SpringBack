package com.example.bunsanedthinking_springback.entity.customer;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 占쏙옙占쏙옙환
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */
@ToString
@NoArgsConstructor
@Data
public class Customer implements Cloneable {

	private List<AccidentHistory> accidentHistoryList;
	private List<Accident> accidentList;
	private String address;
	private int age;
	private String bankAccount;
	private String bankName;
	private List<Complaint> complaintList;
	private List<Contract> contractList;
	private List<Counsel> counselList;
	private List<DiseaseHistory> diseaseHistoryList;
	private Gender gender;
	private int id;
	private String job;
	private String name;
	private String phoneNumber;
	private long property;
	private String residentRegistrationNumber;
	private List<SurgeryHistory> surgeryHistoryList;

	public Customer(String name, String phoneNumber, String job, int age, Gender gender,
		String residentRegistrationNumber, String address, long property, String bankName, String bankAccount) {
		this.accidentList = new ArrayList<>();
		this.address = address;
		this.age = age;
		this.bankAccount = bankAccount;
		this.bankName = bankName;
		this.complaintList = new ArrayList<>();
		this.contractList = new ArrayList<>();
		this.counselList = new ArrayList<>();
		this.gender = gender;
		this.job = job;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.property = property;
		this.residentRegistrationNumber = residentRegistrationNumber;
		this.accidentHistoryList = new ArrayList<>();
		this.diseaseHistoryList = new ArrayList<>();
		this.surgeryHistoryList = new ArrayList<>();
	}

	public Customer(CustomerVO customerVO,
		ArrayList<AccidentHistory> accidentHistories,
		ArrayList<Accident> accidents,
		ArrayList<Counsel> counsels,
		ArrayList<SurgeryHistory> surgeryHistories,
		ArrayList<Complaint> complaints,
		ArrayList<DiseaseHistory> diseaseHistories,
		ArrayList<Contract> contracts) {
		this.id = customerVO.getId();
		this.accidentList = accidents;
		this.address = customerVO.getAddress();
		this.age = customerVO.getAge();
		this.bankAccount = customerVO.getBank_account();
		this.bankName = customerVO.getBank_name();
		this.complaintList = complaints;
		this.contractList = contracts;
		this.counselList = counsels;
		this.gender = Gender.values()[customerVO.getGender()];
		this.job = customerVO.getJob();
		this.name = customerVO.getName();
		this.phoneNumber = customerVO.getPhone_number();
		this.property = customerVO.getProperty();
		this.residentRegistrationNumber = customerVO.getResident_registration_number();
		this.accidentHistoryList = accidentHistories;
		this.diseaseHistoryList = diseaseHistories;
		this.surgeryHistoryList = surgeryHistories;
	}

	public CustomerVO findVO() {
		return new CustomerVO(id, address, age,
			bankAccount, bankName,
			gender.ordinal(), job,
			name, phoneNumber, property,
			residentRegistrationNumber);
	}

	public Customer clone() {

		Customer customer = new Customer(getName(), getPhoneNumber(), getJob(), getAge(), getGender(),
			getResidentRegistrationNumber(), getAddress(), getProperty(), getBankName(), getBankAccount());
		customer.setId(getId());
		customer.setAccidentHistoryList(getAccidentHistoryList());
		customer.setSurgeryHistoryList(getSurgeryHistoryList());
		customer.setDiseaseHistoryList(getDiseaseHistoryList());

		return customer;
	}

}
