package com.example.bunsanedthinking_springback.entity.customer;

import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.accident.AccidentList;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistoryList;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintList;
import com.example.bunsanedthinking_springback.entity.complaint.ComplaintType;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.counsel.CounselList;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistoryList;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoney;
import com.example.bunsanedthinking_springback.entity.insuranceMoney.InsuranceMoneyList;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistoryList;
import com.example.bunsanedthinking_springback.global.exception.AlreadyRequestingException;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.vo.CustomerVO;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author 占쏙옙占쏙옙환
 * @version 1.0
 * @created 27-5-2024 占쏙옙占쏙옙 4:40:41
 */

//2024-06-04 源����쁽
public class Customer implements Cloneable {

	private ArrayList<AccidentHistory> accidentHistoryList;
	private ArrayList<Accident> accidentList;
	private String address;
	private int age;
	private String bankAccount;
	private String bankName;
	private ArrayList<Complaint> complaintList;
	private ArrayList<Contract> contractList;
	private ArrayList<Counsel> counselList;
	private ArrayList<DiseaseHistory> diseaseHistoryList;
	private Gender gender;
	private int id;
	private String job;
	private String name;
	private String phoneNumber;
	private long property;
	private String residentRegistrationNumber;
	private ArrayList<SurgeryHistory> surgeryHistoryList;

	public static final int CUSTOMER_SERIAL_NUMBER = 200;

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

	public Customer(CustomerVO customerVO) {
		this.id = customerVO.getId();
		this.accidentList = new ArrayList<>();
		this.address = customerVO.getAddress();
		this.age = customerVO.getAge();
		this.bankAccount = customerVO.getBank_account();
		this.bankName = customerVO.getBank_name();
		this.complaintList = new ArrayList<>();
		this.contractList = new ArrayList<>();
		this.counselList = new ArrayList<>();
		this.gender = Gender.values()[customerVO.getGender()];
		this.job = customerVO.getJob();
		this.name = customerVO.getName();
		this.phoneNumber = customerVO.getPhone_number();
		this.property = customerVO.getProperty();
		this.residentRegistrationNumber = customerVO.getResident_registration_number();
		this.accidentHistoryList = new ArrayList<>();
		this.diseaseHistoryList = new ArrayList<>();
		this.surgeryHistoryList = new ArrayList<>();
	}

	public Customer() {
	}

	public void signUp(String name, String phoneNumber, String job, int age, Gender gender,
		String residentRegistrationNumber, String address, long property,
		ArrayList<AccidentHistory> tempAccidentHistoryList, ArrayList<SurgeryHistory> tempSurgeryHistoryList,
		ArrayList<DiseaseHistory> tempDiseaseHistoryList, String bankName, String bankAccount,
		CustomerList customerList, AccidentHistoryList accidentHistoryList, SurgeryHistoryList surgeryHistoryList,
		DiseaseHistoryList diseaseHistoryList) throws DuplicateResidentRegistrationNumberException {
		for (Customer customer : customerList.getAll()) {
			if (customer.getResidentRegistrationNumber().equals(residentRegistrationNumber)) {
				throw new DuplicateResidentRegistrationNumberException();
			}
		}

		this.setAccidentList(new ArrayList<>());
		this.setComplaintList(new ArrayList<>());
		this.setCounsel(new ArrayList<>());
		this.setContractList(new ArrayList<>());
		this.setName(name);
		this.setPhoneNumber(phoneNumber);
		this.setJob(job);
		this.setAge(age);
		this.setGender(gender);
		this.setResidentRegistrationNumber(residentRegistrationNumber);
		this.setAddress(address);
		this.setProperty(property);
		this.setBankName(bankName);
		this.setBankAccount(bankAccount);

		customerList.add(this);
		if (tempAccidentHistoryList != null) {
			for (AccidentHistory e : tempAccidentHistoryList) {
				e.setCustomerID(this.getId());
				accidentHistoryList.add(e);
			}
			this.setAccidentHistoryList(tempAccidentHistoryList);
		}
		if (tempSurgeryHistoryList != null) {
			for (SurgeryHistory e : tempSurgeryHistoryList) {
				e.setCustomerID(this.getId());
				surgeryHistoryList.add(e);
			}
			this.setSurgeryHistoryList(tempSurgeryHistoryList);
		}
		if (tempDiseaseHistoryList != null) {
			for (DiseaseHistory e : tempDiseaseHistoryList) {
				e.setCustomer_id(this.getId());
				diseaseHistoryList.add(e);
			}
			this.setDiseaseHistoryList(tempDiseaseHistoryList);
		}
	}

	public void askInsuranceCounsel(Insurance insurance, String name, String phoneNumber,
		Date counselDate, String job, int age, Gender gender, CounselList counselList) {
		Counsel counsel = new Counsel(this.getId(), insurance.getId(), name, phoneNumber, counselDate, job, age,
			gender);
		this.counselList.add(counsel);
		counselList.add(counsel);
	}

	public boolean buyInsurance(Insurance insurance, ContractList contractList) {
		try {
			Contract contract = new Contract(this.id, insurance);
			this.contractList.add(contract);
			contractList.add(contract);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void complain(ComplaintList complaintList, CustomerList customerList, ComplaintType complainType,
		String title, String content) {
		Complaint complaint = new Complaint(complainType, content, this.id, title);
		this.complaintList.add(complaint);
		complaintList.add(complaint);
	}

	public boolean loan(Loan loan, ContractList contractList) throws AlreadyRequestingException {
		ArrayList<Contract> myContractList = null;
		try {
			myContractList = contractList.getAllByCustomer(this.id);
		} catch (NotExistContractException e) {
			myContractList = new ArrayList<>();
		}
		for (Contract contract : myContractList) {
			if (contract.getProduct().getId() == loan.getId()) {
				throw new AlreadyRequestingException();
			}
		}
		Contract contract = new Contract(this.id, loan);
		this.contractList.add(contract);
		contractList.add(contract);
		return true;
	}

	public void receiveInsurance(Contract contract, Image medicalCertificateImage,
		Image receiptImage, Image residentRegistrationCardImage, InsuranceMoneyList insuranceMoneyList)
		throws IOException {
		InsuranceMoney insuranceMoney = new InsuranceMoney(contract.getId(), this.bankName, this.bankAccount,
			medicalCertificateImage, receiptImage, residentRegistrationCardImage);
		insuranceMoneyList.add(insuranceMoney);
	}

	public void reportAccident(String customerName, String customerPhoneNumber, Date date, String location,
		ServiceType serviceType, AccidentList accidentList) {
		Accident accident = new Accident();
		try {
			accident.report(this.id, customerName, customerPhoneNumber, date, location, serviceType);
			int accidentId = accidentList.add(accident);
			this.accidentList.add(accidentList.get(accidentId));
		} catch (NotExistException e) {
			e.getMessage();
		}
	}

	public ArrayList<AccidentHistory> getAccidentHistoryList() {
		return accidentHistoryList;
	}

	public void setAccidentHistoryList(ArrayList<AccidentHistory> accidentHistoryList) {
		this.accidentHistoryList = accidentHistoryList;
	}

	public ArrayList<Accident> getAccidentList() {
		return accidentList;
	}

	public void setAccidentList(ArrayList<Accident> accidentList) {
		this.accidentList = accidentList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public ArrayList<Complaint> getComplaintList() {
		return complaintList;
	}

	public void setComplaintList(ArrayList<Complaint> complaintList) {
		this.complaintList = complaintList;
	}

	public ArrayList<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(ArrayList<Contract> contractList) {
		this.contractList = contractList;
	}

	public ArrayList<Counsel> getCounsel() {
		return counselList;
	}

	public void setCounsel(ArrayList<Counsel> counsel) {
		this.counselList = counsel;
	}

	public ArrayList<DiseaseHistory> getDiseaseHistoryList() {
		return diseaseHistoryList;
	}

	public void setDiseaseHistoryList(ArrayList<DiseaseHistory> diseaseHistoryList) {
		this.diseaseHistoryList = diseaseHistoryList;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getProperty() {
		return property;
	}

	public void setProperty(long property) {
		this.property = property;
	}

	public String getResidentRegistrationNumber() {
		return residentRegistrationNumber;
	}

	public void setResidentRegistrationNumber(String residentRegistrationNumber) {
		this.residentRegistrationNumber = residentRegistrationNumber;
	}

	public ArrayList<SurgeryHistory> getSurgeryHistoryList() {
		return surgeryHistoryList;
	}

	public void setSurgeryHistoryList(ArrayList<SurgeryHistory> surgeryHistoryList) {
		this.surgeryHistoryList = surgeryHistoryList;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankName() {
		return this.bankName;
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
