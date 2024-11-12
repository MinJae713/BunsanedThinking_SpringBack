package com.example.bunsanedthinking_springback.model.service.employee.customerInformationManagement;

import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.AddCustomerInformationDTO;
import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.UpdateCustomerInformationDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.AccidentHistoryDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.DiseaseHistoryDTO;
import com.example.bunsanedthinking_springback.dto.employee.sales.SurgeryHistoryDTO;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.entityModel.accidentHistory.AccidentHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryDModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class CustomerInformationManagementService {

	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private AccidentHistoryEntityModel accidentHistoryEntityModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;
	@Autowired
	private DiseaseHistoryDModel diseaseHistoryDModel;

	public void addCustomerInformation(AddCustomerInformationDTO addCustomerInformationDTO) throws DuplicateResidentRegistrationNumberException {

		boolean isExistCustomerResidentRegistrationNumber = customerDModel.getAll().stream()
				.anyMatch(customer ->
						customer.getResidentRegistrationNumber().equals(addCustomerInformationDTO.getResidentRegistrationNumber()));
		if(isExistCustomerResidentRegistrationNumber)
			throw new DuplicateResidentRegistrationNumberException();

		Integer maxId = customerDModel.getMaxId();
		int id;
		if (maxId == null) {
			id = Integer.parseInt(Customer.CUSTOMER_SERIAL_NUMBER + "1");
		} else {
			String index = (maxId + "").substring((Customer.CUSTOMER_SERIAL_NUMBER + "").length());
			id = Integer.parseInt((Customer.CUSTOMER_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}

		Customer customer = new Customer(
				addCustomerInformationDTO.getName(),
				addCustomerInformationDTO.getPhoneNumber(),
				addCustomerInformationDTO.getJob(),
				addCustomerInformationDTO.getAge(),
				Gender.fromInt(addCustomerInformationDTO.getGender()),
				addCustomerInformationDTO.getResidentRegistrationNumber(),
				addCustomerInformationDTO.getAddress(),
				addCustomerInformationDTO.getProperty(),
				addCustomerInformationDTO.getBankName(),
				addCustomerInformationDTO.getBankAccount()
		);
		customer.setId(id);
		// VO를 DB에 추가
		customerDModel.add(customer);


		if(addCustomerInformationDTO.getAccidentHistoryList() != null) {
			Integer accidentHistoryMaxId = accidentHistoryEntityModel.getMaxId();
			int accidentHistoryId;
			int maxIndex;
			if (accidentHistoryMaxId == null) {
				accidentHistoryId = Integer.parseInt(AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else{
				String index = (accidentHistoryMaxId + "").substring((AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER+ "").length());
				maxIndex = Integer.parseInt(index) + 1;
				accidentHistoryId = Integer.parseInt((AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER+ "") + maxIndex);
			}
			for (AccidentHistoryDTO e : addCustomerInformationDTO.getAccidentHistoryList()) {
				AccidentHistory accidentHistory = new AccidentHistory();
				accidentHistory.setId(accidentHistoryId);

				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				accidentHistory.setDate(date);

				accidentHistory.setAccidentDetail(e.getAccidentDetail());
				accidentHistory.setCustomerID(id);

				customer.getAccidentHistoryList().add(accidentHistory);
				maxIndex++;
				accidentHistoryId = Integer.parseInt((AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
		}

		if(addCustomerInformationDTO.getSurgeryHistoryList() != null) {
			Integer surgeryHistoryMaxId = surgeryHistoryEntityModel.getMaxId();
			int surgeryHistoryId;
			int maxIndex;
			if (surgeryHistoryMaxId == null) {
				surgeryHistoryId = Integer.parseInt(SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else{
				String index = (surgeryHistoryMaxId + "").substring(
						(SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER+ "").length());
				maxIndex = Integer.parseInt(index) + 1;
				surgeryHistoryId = Integer.parseInt((SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER+ "") + maxIndex);
			}
			for (SurgeryHistoryDTO e : addCustomerInformationDTO.getSurgeryHistoryList()) {
				SurgeryHistory surgeryHistory = new SurgeryHistory();
				surgeryHistory.setId(surgeryHistoryId);
				surgeryHistory.setHospitalName(e.getHospitalName());
				surgeryHistory.setName(e.getName());

				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				surgeryHistory.setDate(date);

				surgeryHistory.setCustomerID(id);
				customer.getSurgeryHistoryList().add(surgeryHistory);
				maxIndex++;
				surgeryHistoryId = Integer.parseInt((SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
		}

		if(addCustomerInformationDTO.getDiseaseHistoryList() != null) {
			Integer diseaseHistoryMaxId = diseaseHistoryDModel.getMaxId();
			int diseaseHistoryId;
			int maxIndex;
			if (diseaseHistoryMaxId == null) {
				diseaseHistoryId = Integer.parseInt(DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else {
				String index = (diseaseHistoryMaxId + "").substring(
						(DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER+ "").length());
				maxIndex = Integer.parseInt(index) + 1;
				diseaseHistoryId = Integer.parseInt((DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER+ "") + maxIndex);
			}
			for (DiseaseHistoryDTO e : addCustomerInformationDTO.getDiseaseHistoryList()) {
				DiseaseHistory diseaseHistory = new DiseaseHistory();
				diseaseHistory.setId(diseaseHistoryId);

				LocalDate localDate = LocalDate.parse(e.getDate_of_diagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				diseaseHistory.setDate_of_diagnosis(date);

				diseaseHistory.setName(e.getName());
				diseaseHistory.setCustomer_id(id);
				customer.getDiseaseHistoryList().add(diseaseHistory);
				maxIndex++;
				diseaseHistoryId = Integer.parseInt((DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "") + maxIndex);
			}
		}
	}

	public void deleteCustomerInformation(int id) throws NotExistException {
		Customer customer = customerDModel.getById(id);
		if (customer == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		customerDModel.delete(id);
	}

	public Customer getCustomerInformation(int id) throws NotExistException {
		Customer customer = customerDModel.getById(id);
		if (customer == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		return customer;
	}

	public void updateCustomerInformation(UpdateCustomerInformationDTO updateCustomerInformationDTO)
			throws NotExistException{
		int id = updateCustomerInformationDTO.getId();
		int index = updateCustomerInformationDTO.getIndex();
		String input = updateCustomerInformationDTO.getInput();
		Customer customer = customerDModel.getById(id);
		if (customer == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1:
				customer.setName(input);
				customerDModel.update(customer);
				break;
			case 2:
				customer.setPhoneNumber(input);
				customerDModel.update(customer);
				break;
			case 3:
				customer.setJob(input);
				customerDModel.update(customer);
				break;
			case 4:
				customer.setAge(Integer.parseInt(input));
				customerDModel.update(customer);
				break;
			case 5:
				customer.setGender(Gender.fromInt(Integer.parseInt(input)));
				customerDModel.update(customer);
				break;
			case 6:
				customer.setAddress(input);
				customerDModel.update(customer);
				break;
			case 7:
				customer.setProperty((long)(Integer.parseInt(input)));
				customerDModel.update(customer);
				break;
			case 8:
				customer.setBankName(input);
				customerDModel.update(customer);
				break;
			case 9:
				customer.setBankAccount(input);
				customerDModel.update(customer);
				break;
			default:
				break;
		}
	}

	public List<Customer> getAll() {
		return customerDModel.getAll();
	}
}
