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
import java.util.List;

@RestController
@RequestMapping("/employee/customerInformationManagement")
public class CustomerInformationManagementController {
	@Autowired
	private CustomerInformationManagementModel customerInformationManagementModel;

	public void addCustomerInformation(String name, String phoneNumber, String job, int age, Gender gender,
									   String residentRegistrationNumber, String address, long property,
									   List<AccidentHistory> tempAccidentHistoryList,
									   List<SurgeryHistory> tempSurgeryHistoryList,
									   List<DiseaseHistory> tempDiseaseHistoryList,
									   String bankName, String bankAccount) throws DuplicateResidentRegistrationNumberException {
		customerInformationManagementModel.addCustomerInformation(name, phoneNumber, job, age, gender, 
				residentRegistrationNumber, address, property, tempAccidentHistoryList, tempSurgeryHistoryList, 
				tempDiseaseHistoryList, bankName, bankAccount);
	}

	public void deleteCustomerInformation(int id) throws NotExistException {
		customerInformationManagementModel.deleteCustomerInformation(id);
	}
	public Customer getCustomerInformation(int id) throws NotExistException{
		return customerInformationManagementModel.getCustomerInformation(id);
	}
	public void updateCustomerInformation(int index, String input, int id) throws DuplicateResidentRegistrationNumberException, NotExistException{
		customerInformationManagementModel.updateCustomerInformation(index, input, id);
	}
	public List<Customer> getAll() {
		return customerInformationManagementModel.getAll();
	}
}
