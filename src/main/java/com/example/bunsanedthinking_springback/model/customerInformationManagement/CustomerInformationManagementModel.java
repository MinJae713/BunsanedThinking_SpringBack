package com.example.bunsanedthinking_springback.model.customerInformationManagement;


import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AccidentHistoryMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseHistoryMapper;
import com.example.bunsanedthinking_springback.repository.SurgeryHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerInformationManagementModel {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private AccidentHistoryMapper accidentHistoryMapper;
	@Autowired
	private SurgeryHistoryMapper surgeryHistoryMapper;
	@Autowired
	private DiseaseHistoryMapper diseaseHistoryMapper;

	public void addCustomerInformation(String name, String phoneNumber, String job, int age, Gender gender,
									   String residentRegistrationNumber, String address, long property,
									   List<AccidentHistory> tempAccidentHistoryList,
									   List<SurgeryHistory> tempSurgeryHistoryList,
									   List<DiseaseHistory> tempDiseaseHistoryList,
									   String bankName, String bankAccount) throws DuplicateResidentRegistrationNumberException {
		if (customerMapper.findByResidentRegistrationNumber_CustomerInformationManagement(residentRegistrationNumber) != null) {
			throw new DuplicateResidentRegistrationNumberException();
		}
		
		Customer customer = new Customer(name, phoneNumber, job, age, gender, residentRegistrationNumber, address, property, bankName, bankAccount);
		customerMapper.insert_CustomerInformationManagement(customer);

		if(tempAccidentHistoryList != null) {
			for(AccidentHistory accident : tempAccidentHistoryList) {
				accident.setCustomerID(customer.getId());
				accidentHistoryMapper.insert_accidentHistory_CustomerInformationManagement(accident);
			}
		}
		if(tempSurgeryHistoryList != null) {
			for(SurgeryHistory surgery : tempSurgeryHistoryList) {
				surgery.setCustomerID(customer.getId());
				surgeryHistoryMapper.insert_surgeryHistory_CustomerInformationManagement(surgery);
			}
		}
		if(tempDiseaseHistoryList != null) {
			for(DiseaseHistory disease : tempDiseaseHistoryList) {
				disease.setCustomer_id(customer.getId());
				diseaseHistoryMapper.insert_diseaseHistory_CustomerInformationManagement(disease);
			}
		}
	}

	public void deleteCustomerInformation(int id) throws NotExistException {
		if (customerMapper.findById_CustomerInformationManagement(id) == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		accidentHistoryMapper.deleteAccidentHistoriesByCustomerId_CustomerInformationManagement(id);
		surgeryHistoryMapper.deleteSurgeryHistoriesByCustomerId_CustomerInformationManagement(id);
		diseaseHistoryMapper.deleteDiseaseHistoriesByCustomerId_CustomerInformationManagement(id);
		customerMapper.delete_CustomerInformationManagement(id);
	}

	public Customer getCustomerInformation(int id) throws NotExistException{
		Customer customer = customerMapper.findById_CustomerInformationManagement(id);
		if (customer == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		return customer;
	}

	public void updateCustomerInformation(int index, String input, int id) throws NotExistException{
		Customer customer = getCustomerInformation(id);

		switch (index) {
		case 1:
			customer.setName(input);
			break;
		case 2:
			customer.setPhoneNumber(input);
			break;
		case 3:
			customer.setJob(input);
			break;
		case 4:
			customer.setAge(Integer.parseInt(input));
			break;
		case 5:
			if ("Male".equalsIgnoreCase(input)) {
				customer.setGender(Gender.Male);
			} else if ("Female".equalsIgnoreCase(input)) {
				customer.setGender(Gender.Female);
			}
			break;
		case 6:
			customer.setAddress(input);
			break;
		case 7:
			customer.setProperty((Integer.parseInt(input)));
			break;
		case 11:
			customer.setBankName(input);
			break;
		case 12:
			customer.setBankAccount(input);
			break;
		default:
			throw new IllegalArgumentException("유효하지 않은 선택입니다. 올바른 값을 입력하세요.");
		}
		customerMapper.update_CustomerInformationManagement(customer);
	}
	public List<Customer> getAll() {
		return customerMapper.getAll_CustomerInformationManagement();
	}
}