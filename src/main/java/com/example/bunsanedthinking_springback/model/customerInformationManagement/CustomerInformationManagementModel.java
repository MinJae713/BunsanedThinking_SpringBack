package com.example.bunsanedthinking_springback.model.customerInformationManagement;


import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistoryList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistoryList;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistoryList;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerInformationManagementModel {

	public void addCustomerInformation(String name, String phoneNumber, String job, int age, Gender gender, String residentRegistrationNumber, String address, long property, ArrayList<AccidentHistory> tempAccidentHistoryList, ArrayList<SurgeryHistory> tempSurgeryHistoryList, ArrayList<DiseaseHistory> tempDiseaseHistoryList, String bankName, String bankAccount, CustomerList customerList, AccidentHistoryList accidentHistoryList, SurgeryHistoryList surgeryHistoryList, DiseaseHistoryList diseaseHistoryList) throws DuplicateResidentRegistrationNumberException {
		for (Customer customer : customerList.getAll()) {
			if (customer.getResidentRegistrationNumber().equals(residentRegistrationNumber)) {
				throw new DuplicateResidentRegistrationNumberException();
			}
		}
		
		Customer customer = new Customer(name, phoneNumber, job, age, gender, residentRegistrationNumber, address, property, bankName, bankAccount);
		customerList.add(customer);
		if(tempAccidentHistoryList != null) {
			for(AccidentHistory e :tempAccidentHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setAccidentHistoryList(tempAccidentHistoryList);
				accidentHistoryList.add(e);
			}
		}
		if(tempSurgeryHistoryList != null) {
			for(SurgeryHistory e :tempSurgeryHistoryList) {
				e.setCustomerID(customer.getId());
				customer.setSurgeryHistoryList(tempSurgeryHistoryList);
				surgeryHistoryList.add(e);
			}
		}
		if(tempDiseaseHistoryList != null) {
			for(DiseaseHistory e :tempDiseaseHistoryList) {
				e.setCustomer_id(customer.getId());
				customer.setDiseaseHistoryList(tempDiseaseHistoryList);
				diseaseHistoryList.add(e);
			}
		}
	}

	public void deleteCustomerInformation(CustomerList customerList, int id) throws NotExistException {
		customerList.delete(id);
	}

	public Customer getCustomerInformation(CustomerList customerList, int id) throws NotExistException{
		try {
			return customerList.get(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
	}

	public void updateCustomerInformation(int index, String input, Customer customer, CustomerList customerList) throws DuplicateResidentRegistrationNumberException, NotExistException{
		switch (index) {
		case 1:
			customer.setName(input);
			customerList.update(customer);
			break;
		case 2:
			customer.setPhoneNumber(input);
			customerList.update(customer);
			break;
		case 3:
			customer.setJob(input);
			customerList.update(customer);
			break;
		case 4:
			customer.setAge(Integer.parseInt(input));
			customerList.update(customer);
			break;
		case 5:
			if (Gender.Male.ordinal() == (Integer.parseInt(input) - 1)) {
				customer.setGender(Gender.Male);
			} else if (Gender.Female.ordinal() == (Integer.parseInt(input) - 1)) {
				customer.setGender(Gender.Female);
			}
			break;
		case 6:
			customer.setAddress(input);
			customerList.update(customer);
			break;
		case 7:
			customer.setProperty((Integer.parseInt(input)));
			customerList.update(customer);
			break;
		case 11:
			customer.setBankName(input);
			customerList.update(customer);
			break;
		case 12:
			customer.setBankAccount(input);
			customerList.update(customer);
			break;
		default:
			break;
		}
	}
	public ArrayList<Customer> getAll(CustomerList customerList) {
		return customerList.getAll();
	}
}