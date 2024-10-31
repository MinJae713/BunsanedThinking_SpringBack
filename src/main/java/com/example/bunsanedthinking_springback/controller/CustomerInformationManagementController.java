package com.example.bunsanedthinking_springback.controller;

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
import com.example.bunsanedthinking_springback.model.customerInformationManagement.CustomerInformationManagementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/customerInformationManagement")
public class CustomerInformationManagementController {
	@Autowired
	private CustomerInformationManagementModel customerInformationManagementModel;

	public void addCustomerInformation(String name, String phoneNumber, String job, int age, Gender gender,
									   String residentRegistrationNumber, String address, long property, ArrayList<AccidentHistory> tempAccidentHistoryList,
									   ArrayList<SurgeryHistory> tempSurgeryHistoryList, ArrayList<DiseaseHistory> tempDiseaseHistoryList, String bankName,
									   String bankAccount, CustomerList customerList, AccidentHistoryList accidentHistoryList,
									   SurgeryHistoryList surgeryHistoryList, DiseaseHistoryList diseaseHistoryList) throws DuplicateResidentRegistrationNumberException {
		customerInformationManagementModel.addCustomerInformation(name, phoneNumber, job, age, gender, 
				residentRegistrationNumber, address, property, tempAccidentHistoryList, tempSurgeryHistoryList, 
				tempDiseaseHistoryList, bankName, bankAccount, customerList, accidentHistoryList, 
				surgeryHistoryList, diseaseHistoryList);
	}

	public void deleteCustomerInformation(CustomerList customerList, int id) throws NotExistException {
		customerInformationManagementModel.deleteCustomerInformation(customerList, id);
	}
	public Customer getCustomerInformation(CustomerList customerList, int id) throws NotExistException{
		return customerInformationManagementModel.getCustomerInformation(customerList, id);
	}
	public void updateCustomerInformation(int index, String input, Customer customer, CustomerList customerList) throws DuplicateResidentRegistrationNumberException, NotExistException{
		customerInformationManagementModel.updateCustomerInformation(index, input, customer, customerList);
	}
	public ArrayList<Customer> getAll(CustomerList customerList) {
		return customerInformationManagementModel.getAll(customerList);
	}
}
