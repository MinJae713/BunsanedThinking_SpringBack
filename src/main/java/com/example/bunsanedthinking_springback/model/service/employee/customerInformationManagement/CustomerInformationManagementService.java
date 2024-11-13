package com.example.bunsanedthinking_springback.model.service.employee.customerInformationManagement;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;

@Service
public class CustomerInformationManagementService {

	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private AccidentHistoryEntityModel accidentHistoryEntityModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;
	@Autowired
	private DiseaseHistoryEntityModel diseaseHistoryEntityModel;

	@Value("${serials.customer}")
	public int CUSTOMER_SERIAL_NUMBER;
	@Value("${serials.accidentHistory}")
	public int ACCIDENT_HISTORY_SERIAL_NUMBER;
	@Value("${serials.surgeryHistory}")
	public int SURGERY_HISTORY_SERIAL_NUMBER;
	@Value("${serials.diseaseHistory}")
	public int DISEASE_HISTORY_SERIAL_NUMBER;

	public void addCustomerInformation(AddCustomerInformationDTO addCustomerInformationDTO) throws DuplicateResidentRegistrationNumberException {

		boolean isExistCustomerResidentRegistrationNumber = customerEntityModel.getAll().stream()
				.anyMatch(customer -> customer.getResidentRegistrationNumber().equals(addCustomerInformationDTO.getResidentRegistrationNumber()));
		if(isExistCustomerResidentRegistrationNumber)
			throw new DuplicateResidentRegistrationNumberException();

		Integer maxId = customerEntityModel.getMaxId();
		int id = NextIdGetter.getNextId(maxId, CUSTOMER_SERIAL_NUMBER);

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
		customerEntityModel.add(customer);

		if (addCustomerInformationDTO.getAccidentHistoryList() != null) {
			Integer accidentHistoryMaxId = accidentHistoryEntityModel.getMaxId();
			int accidentHistoryId = NextIdGetter.getNextId(accidentHistoryMaxId, ACCIDENT_HISTORY_SERIAL_NUMBER);

			for (AccidentHistoryDTO e : addCustomerInformationDTO.getAccidentHistoryList()) {
				AccidentHistory accidentHistory = new AccidentHistory();
				accidentHistory.setId(accidentHistoryId);

				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				accidentHistory.setDate(date);

				accidentHistory.setAccidentDetail(e.getAccidentDetail());
				accidentHistory.setCustomerID(id);

				customer.getAccidentHistoryList().add(accidentHistory);
				accidentHistoryEntityModel.add(accidentHistory);

				accidentHistoryId = NextIdGetter.getNextId(accidentHistoryId, ACCIDENT_HISTORY_SERIAL_NUMBER);
			}
		}

		if (addCustomerInformationDTO.getSurgeryHistoryList() != null) {
			Integer surgeryHistoryMaxId = surgeryHistoryEntityModel.getMaxId();
			int surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryMaxId, SURGERY_HISTORY_SERIAL_NUMBER);

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
				surgeryHistoryEntityModel.add(surgeryHistory);

				surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryId, SURGERY_HISTORY_SERIAL_NUMBER);
			}
		}

		if (addCustomerInformationDTO.getDiseaseHistoryList() != null) {
			Integer diseaseHistoryMaxId = diseaseHistoryEntityModel.getMaxId();
			int diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryMaxId, DISEASE_HISTORY_SERIAL_NUMBER);

			for (DiseaseHistoryDTO e : addCustomerInformationDTO.getDiseaseHistoryList()) {
				DiseaseHistory diseaseHistory = new DiseaseHistory();
				diseaseHistory.setId(diseaseHistoryId);

				LocalDate localDate = LocalDate.parse(e.getDateOfDiagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				diseaseHistory.setDate_of_diagnosis(date);

				diseaseHistory.setName(e.getName());
				diseaseHistory.setCustomer_id(id);

				customer.getDiseaseHistoryList().add(diseaseHistory);
				diseaseHistoryEntityModel.add(diseaseHistory);

				diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryId, DISEASE_HISTORY_SERIAL_NUMBER);
			}
		}
	}

	public void deleteCustomerInformation(int id) throws NotExistException {
		Customer customer = customerEntityModel.getById(id);
		if (customer == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		customerEntityModel.delete(id);
	}

	public Customer getCustomerInformation(int id) throws NotExistException {
		Customer customer = customerEntityModel.getById(id);
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
		Customer customer = customerEntityModel.getById(id);
		if (customer == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		switch (index) {
			case 1:
				customer.setName(input);
				customerEntityModel.update(customer);
				break;
			case 2:
				customer.setPhoneNumber(input);
				customerEntityModel.update(customer);
				break;
			case 3:
				customer.setJob(input);
				customerEntityModel.update(customer);
				break;
			case 4:
				customer.setAge(Integer.parseInt(input));
				customerEntityModel.update(customer);
				break;
			case 5:
				customer.setGender(Gender.fromInt(Integer.parseInt(input)));
				customerEntityModel.update(customer);
				break;
			case 6:
				customer.setAddress(input);
				customerEntityModel.update(customer);
				break;
			case 7:
				customer.setProperty((long)(Integer.parseInt(input)));
				customerEntityModel.update(customer);
				break;
			case 8:
				customer.setBankName(input);
				customerEntityModel.update(customer);
				break;
			case 9:
				customer.setBankAccount(input);
				customerEntityModel.update(customer);
				break;
			default:
				break;
		}
	}

	public List<Customer> getAll() {
		return customerEntityModel.getAll();
	}
}
