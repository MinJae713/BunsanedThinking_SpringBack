package com.example.bunsanedthinking_springback.model.service.employee.customerInformationManagement;

import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request.*;
import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response.CustomerResponse;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.serial.Serial;
import com.example.bunsanedthinking_springback.global.constants.service.employee.customerInformationManagement.CustomerInformationManagementConstants;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.accidentHistory.AccidentHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryEntityModel;
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
	private Serial serial;

	@Autowired
	private CustomerEntityModel customerEntityModel;
	@Autowired
	private AccidentHistoryEntityModel accidentHistoryEntityModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;
	@Autowired
	private DiseaseHistoryEntityModel diseaseHistoryEntityModel;

	public void addCustomerInformation(AddCustomerInformationRequest addCustomerInformationRequest) throws DuplicateResidentRegistrationNumberException {

		boolean isExistCustomerResidentRegistrationNumber = customerEntityModel.getAll().stream()
				.anyMatch(customer -> customer.getResidentRegistrationNumber().equals(addCustomerInformationRequest.getResidentRegistrationNumber()));
		if(isExistCustomerResidentRegistrationNumber)
			throw new DuplicateResidentRegistrationNumberException();

		Integer maxId = customerEntityModel.getMaxId();
		int id = NextIdGetter.getNextId(maxId, serial.getCustomer());

		Customer customer = new Customer(
				addCustomerInformationRequest.getName(),
				addCustomerInformationRequest.getPhoneNumber(),
				addCustomerInformationRequest.getJob(),
				addCustomerInformationRequest.getAge(),
				Gender.fromInt(addCustomerInformationRequest.getGender()),
				addCustomerInformationRequest.getResidentRegistrationNumber(),
				addCustomerInformationRequest.getAddress(),
				addCustomerInformationRequest.getProperty(),
				addCustomerInformationRequest.getBankName(),
				addCustomerInformationRequest.getBankAccount()
		);
		customer.setId(id);
		// VO를 DB에 추가
		customerEntityModel.add(customer);

		if (addCustomerInformationRequest.getAccidentHistoryList() != null) {
			Integer accidentHistoryMaxId = accidentHistoryEntityModel.getMaxId();
			int accidentHistoryId = NextIdGetter.getNextId(accidentHistoryMaxId, serial.getAccidentHistory());

			for (AddAccidentHistoryRequest e : addCustomerInformationRequest.getAccidentHistoryList()) {
				AccidentHistory accidentHistory = new AccidentHistory();
				accidentHistory.setId(accidentHistoryId);

				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				accidentHistory.setDate(date);

				accidentHistory.setAccidentDetail(e.getAccidentDetail());
				accidentHistory.setCustomerID(id);

				customer.getAccidentHistoryList().add(accidentHistory);
				accidentHistoryEntityModel.add(accidentHistory);

				accidentHistoryId = NextIdGetter.getNextId(accidentHistoryId, serial.getAccidentHistory());
			}
		}

		if (addCustomerInformationRequest.getSurgeryHistoryList() != null) {
			Integer surgeryHistoryMaxId = surgeryHistoryEntityModel.getMaxId();
			int surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryMaxId, serial.getSurgeryHistory());

			for (AddSurgeryHistoryRequest e : addCustomerInformationRequest.getSurgeryHistoryList()) {
				SurgeryHistory surgeryHistory = new SurgeryHistory();
				surgeryHistory.setId(surgeryHistoryId);
				surgeryHistory.setHospitalName(e.getHospitalName());
				surgeryHistory.setName(e.getName());

				LocalDate localDate = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				surgeryHistory.setDate(date);

				surgeryHistory.setCustomerID(id);

				customer.getSurgeryHistoryList().add(surgeryHistory);
				surgeryHistoryEntityModel.add(surgeryHistory);

				surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryId, serial.getSurgeryHistory());
			}
		}

		if (addCustomerInformationRequest.getDiseaseHistoryList() != null) {
			Integer diseaseHistoryMaxId = diseaseHistoryEntityModel.getMaxId();
			int diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryMaxId, serial.getDiseaseHistory());

			for (AddDiseaseHistoryRequest e : addCustomerInformationRequest.getDiseaseHistoryList()) {
				DiseaseHistory diseaseHistory = new DiseaseHistory();
				diseaseHistory.setId(diseaseHistoryId);

				LocalDate localDate = LocalDate.parse(e.getDateOfDiagnosis(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
				Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
				diseaseHistory.setDate_of_diagnosis(date);

				diseaseHistory.setName(e.getName());
				diseaseHistory.setCustomer_id(id);

				customer.getDiseaseHistoryList().add(diseaseHistory);
				diseaseHistoryEntityModel.add(diseaseHistory);

				diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryId, serial.getDiseaseHistory());
			}
		}
	}

	public void deleteCustomerInformation(int id) throws NotExistException {
		Customer customer = customerEntityModel.getById(id);
		if (customer == null) {
			throw new NotExistException(CustomerInformationManagementConstants.CUSTOMER_INFORMATION_NULL);
		}
		customerEntityModel.delete(id);
	}

	public CustomerResponse getCustomerInformation(int id) throws NotExistException {
		Customer customer = customerEntityModel.getById(id);
		if (customer == null) {
			throw new NotExistException(CustomerInformationManagementConstants.CUSTOMER_INFORMATION_NULL);
		}
		return CustomerResponse.from(customer);
	}

	public void updateCustomerInformation(UpdateCustomerInformationRequest updateCustomerInformationRequest)
			throws NotExistException{
		int id = updateCustomerInformationRequest.getId();
		int index = updateCustomerInformationRequest.getIndex();
		String input = updateCustomerInformationRequest.getInput();
		Customer customer = customerEntityModel.getById(id);
		if (customer == null) {
			throw new NotExistException(CustomerInformationManagementConstants.CUSTOMER_INFORMATION_NULL);
		}
		switch (index) {
			case 1:
				if (input.length() > 20) {
					throw new RuntimeException(CustomerInformationManagementConstants.NAME_LENGTH_MESSAGE);
				}
				if (!input.matches(CustomerInformationManagementConstants.NAME_FORMAT_REGEXP))
					throw new RuntimeException(CustomerInformationManagementConstants.NAME_FORMAT_MESSAGE);
				customer.setName(input);
				customerEntityModel.update(customer);
				break;
			case 2:
				if (input.length() != 13) {
					throw new RuntimeException(CustomerInformationManagementConstants.PHONE_NUMBER_LENGTH_MESSAGE);
				}
				if (!input.matches(CustomerInformationManagementConstants.PHONE_NUMBER_FORMAT_REGEXP)) {
					throw new RuntimeException(CustomerInformationManagementConstants.PHONE_NUMBER_FORMAT_MESSAGE);
				}
				customer.setPhoneNumber(input);
				customerEntityModel.update(customer);
				break;
			case 3:
				if (input.length() > 20) {
					throw new RuntimeException(CustomerInformationManagementConstants.JOB_LENGTH_MESSAGE);
				}
				if (!input.matches(CustomerInformationManagementConstants.JOB_FORMAT_REGEXP)) {
					throw new RuntimeException(CustomerInformationManagementConstants.JOB_FORMAT_MESSAGE);
				}
				customer.setJob(input);
				customerEntityModel.update(customer);
				break;
			case 4:
				int age;
				try {
					age = Integer.parseInt(input);
				} catch (NumberFormatException e) {
					throw new RuntimeException(CustomerInformationManagementConstants.AGE_INVALID_MESSAGE);
				}
				if (age < 1) {
					throw new RuntimeException(CustomerInformationManagementConstants.AGE_MIN_VALUE);
				}
				if (age > 120) {
					throw new RuntimeException(CustomerInformationManagementConstants.AGE_MAX_VALUE);
				}
				customer.setAge(age);
				customerEntityModel.update(customer);
				break;
			case 5:
				customer.setGender(Gender.fromInt(Integer.parseInt(input)));
				customerEntityModel.update(customer);
				break;
			case 6:
				if (input.length() > 100) {
					throw new RuntimeException(CustomerInformationManagementConstants.ADDRESS_LENGTH_MESSAGE);
				}
				customer.setAddress(input);
				customerEntityModel.update(customer);
				break;
			case 7:
				long property;
				try {
					property = Long.parseLong(input);
				} catch (NumberFormatException e) {
					throw new RuntimeException(CustomerInformationManagementConstants.PROPERTY_INVALID_MESSAGE);
				}
				if (property < 1) {
					throw new RuntimeException(CustomerInformationManagementConstants.PROPERTY_MIN_VALUE);
				}
				customer.setProperty(property);
				customerEntityModel.update(customer);
				break;
			case 8:
				if (input.length() > 10) {
					throw new RuntimeException(CustomerInformationManagementConstants.BANK_NAME_LENGTH_MESSAGE);
				}
				if (!input.matches(CustomerInformationManagementConstants.BANK_NAME_FORMAT_REGEXP)) {
					throw new RuntimeException(CustomerInformationManagementConstants.BANK_NAME_FORMAT_MESSAGE);
				}
				customer.setBankName(input);
				customerEntityModel.update(customer);
				break;
			case 9:
				if (input.length() > 20) {
					throw new RuntimeException(CustomerInformationManagementConstants.BANK_ACCOUNT_LENGTH_MESSAGE);
				}
				if (!input.matches(CustomerInformationManagementConstants.BANK_ACCOUNT_FORMAT_REGEXP)) {
					throw new RuntimeException(CustomerInformationManagementConstants.BANK_ACCOUNT_FORMAT_MESSAGE);
				}
				customer.setBankAccount(input);
				customerEntityModel.update(customer);
				break;
			case 10:
				updateAccidentHistories(updateCustomerInformationRequest.getAccidentHistoryList(), customer);
				break;
			case 11:
				updateSurgeryHistories(updateCustomerInformationRequest.getSurgeryHistoryList(), customer);
				break;
			case 12:
				updateDiseaseHistories(updateCustomerInformationRequest.getDiseaseHistoryList(), customer);
				break;
		}
	}

	private void updateAccidentHistories(List<UpdateAccidentHistoryRequest> accidentHistoryList, Customer customer) {
		if (accidentHistoryList != null) {
			for (UpdateAccidentHistoryRequest dto : accidentHistoryList) {
				try {
					// ID가 0 이하인 경우 무시 (업데이트만 수행)
					if (dto.getId() <= 0) {
						System.err.println(CustomerInformationManagementConstants.INVALID_ACCIDENT_ID + dto);
						continue;
					}
					// 기존 사고 이력을 ID로 조회
					AccidentHistory accidentHistory = accidentHistoryEntityModel.getById(dto.getId());
					if (accidentHistory == null) {
						System.err.println(CustomerInformationManagementConstants.ACCIDENT_HISTORY_NOT_FOUND + dto.getId());
						continue;
					}
					if (accidentHistory.getCustomerID() != customer.getId()) {
						System.err.println(CustomerInformationManagementConstants.NOT_CUSTOMER_ASSIGNED_ACCIDENT_HISTORY + dto.getId());
						continue; // 해당 이력 무시
					}
					// 날짜 값이 유효한 경우 변환
					Date date = null;
					if (dto.getDate() != null && !dto.getDate().isEmpty()) {
						LocalDate localDate = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
						date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					}
					// 기존 사고 이력 업데이트
					if (date != null) {
						accidentHistory.setDate(date);
					}
					accidentHistory.setAccidentDetail(dto.getAccidentDetail());
					accidentHistoryEntityModel.update(accidentHistory);
				} catch (Exception e) {
					System.err.println(CustomerInformationManagementConstants.UPDATE_ACCIDENT_ERROR + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	private void updateSurgeryHistories(List<UpdateSurgeryHistoryRequest> surgeryHistoryList, Customer customer) {
		if (surgeryHistoryList != null) {
			for (UpdateSurgeryHistoryRequest dto : surgeryHistoryList) {
				try {
					// ID가 0 이하인 경우 무시 (업데이트만 수행)
					if (dto.getId() <= 0) {
						System.err.println(CustomerInformationManagementConstants.INVALID_SURGERY_ID + dto);
						continue;
					}
					// 기존 수술 이력을 ID로 조회
					SurgeryHistory surgeryHistory = surgeryHistoryEntityModel.getById(dto.getId());
					if (surgeryHistory == null) {
						System.err.println(CustomerInformationManagementConstants.SURGERY_HISTORY_NOT_FOUND + dto.getId());
						continue;
					}
					// 고객 소유권 확인
					if (surgeryHistory.getCustomerID() != customer.getId()) {
						System.err.println(CustomerInformationManagementConstants.NOT_CUSTOMER_ASSIGNED_SURGERY_HISTORY + dto.getId());
						continue; // 해당 이력 무시
					}
					// 날짜 값이 유효한 경우 변환
					Date date = null;
					if (dto.getDate() != null && !dto.getDate().isEmpty()) {
						LocalDate localDate = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
						date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					}
					// 기존 수술 이력 업데이트
					if (date != null) {
						surgeryHistory.setDate(date);
					}
					surgeryHistory.setHospitalName(dto.getHospitalName());
					surgeryHistory.setName(dto.getName());
					surgeryHistoryEntityModel.update(surgeryHistory);

				} catch (Exception e) {
					System.err.println(CustomerInformationManagementConstants.UPDATE_SURGERY_ERROR + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	private void updateDiseaseHistories(List<UpdateDiseaseHistoryRequest> diseaseHistoryList, Customer customer) {
		if (diseaseHistoryList != null) {
			for (UpdateDiseaseHistoryRequest dto : diseaseHistoryList) {
				try {
					// ID가 0 이하인 경우 무시 (업데이트만 수행)
					if (dto.getId() <= 0) {
						System.err.println(CustomerInformationManagementConstants.INVALID_DISEASE_ID + dto);
						continue;
					}
					// 기존 병력을 ID로 조회
					DiseaseHistory diseaseHistory = diseaseHistoryEntityModel.getById(dto.getId());
					if (diseaseHistory == null) {
						System.err.println(CustomerInformationManagementConstants.DISEASE_HISTORY_NOT_FOUND + dto.getId());
						continue;
					}
					// 고객 소유권 확인
					if (diseaseHistory.getCustomer_id() != customer.getId()) {
						System.err.println(CustomerInformationManagementConstants.NOT_CUSTOMER_ASSIGNED_DISEASE_HISTORY + dto.getId());
						continue; // 해당 이력 무시
					}
					// 날짜 값이 유효한 경우 변환
					Date dateOfDiagnosis = null;
					if (dto.getDateOfDiagnosis() != null && !dto.getDateOfDiagnosis().isEmpty()) {
						LocalDate localDate = LocalDate.parse(dto.getDateOfDiagnosis(), DateTimeFormatter.ofPattern(CommonConstants.DATE_FORMAT));
						dateOfDiagnosis = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					}
					// 기존 병력 업데이트
					if (dateOfDiagnosis != null) {
						diseaseHistory.setDate_of_diagnosis(dateOfDiagnosis); // 유효한 날짜만 설정
					}
					diseaseHistory.setName(dto.getName());
					diseaseHistoryEntityModel.update(diseaseHistory); // 업데이트 호출

				} catch (Exception e) {
					System.err.println(CustomerInformationManagementConstants.UPDATE_DISEASE_ERROR + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public List<CustomerResponse> getAll() {
		return customerEntityModel.getAll().stream()
				.map(CustomerResponse::from)
				.toList();
	}
}
