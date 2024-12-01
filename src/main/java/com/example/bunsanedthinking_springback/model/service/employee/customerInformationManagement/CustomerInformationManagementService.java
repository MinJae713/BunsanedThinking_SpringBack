package com.example.bunsanedthinking_springback.model.service.employee.customerInformationManagement;

import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request.*;
import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response.CustomerResponse;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.accidentHistory.AccidentHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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

	public void addCustomerInformation(AddCustomerInformationRequest addCustomerInformationRequest) throws DuplicateResidentRegistrationNumberException {

		boolean isExistCustomerResidentRegistrationNumber = customerEntityModel.getAll().stream()
				.anyMatch(customer -> customer.getResidentRegistrationNumber().equals(addCustomerInformationRequest.getResidentRegistrationNumber()));
		if(isExistCustomerResidentRegistrationNumber)
			throw new DuplicateResidentRegistrationNumberException();

		Integer maxId = customerEntityModel.getMaxId();
		int id = NextIdGetter.getNextId(maxId, CUSTOMER_SERIAL_NUMBER);

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
			int accidentHistoryId = NextIdGetter.getNextId(accidentHistoryMaxId, ACCIDENT_HISTORY_SERIAL_NUMBER);

			for (AddAccidentHistoryReuqest e : addCustomerInformationRequest.getAccidentHistoryList()) {
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

		if (addCustomerInformationRequest.getSurgeryHistoryList() != null) {
			Integer surgeryHistoryMaxId = surgeryHistoryEntityModel.getMaxId();
			int surgeryHistoryId = NextIdGetter.getNextId(surgeryHistoryMaxId, SURGERY_HISTORY_SERIAL_NUMBER);

			for (AddSurgeryHistoryRequest e : addCustomerInformationRequest.getSurgeryHistoryList()) {
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

		if (addCustomerInformationRequest.getDiseaseHistoryList() != null) {
			Integer diseaseHistoryMaxId = diseaseHistoryEntityModel.getMaxId();
			int diseaseHistoryId = NextIdGetter.getNextId(diseaseHistoryMaxId, DISEASE_HISTORY_SERIAL_NUMBER);

			for (AddDiseaseHistoryRequest e : addCustomerInformationRequest.getDiseaseHistoryList()) {
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

	public void updateCustomerInformation(UpdateCustomerInformationRequest updateCustomerInformationRequest)
			throws NotExistException{
		int id = updateCustomerInformationRequest.getId();
		int index = updateCustomerInformationRequest.getIndex();
		String input = updateCustomerInformationRequest.getInput();
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
			case 10:
				updateAccidentHistories(updateCustomerInformationRequest.getAccidentHistoryList(), customer);
				break;
			case 11:
				updateSurgeryHistories(updateCustomerInformationRequest.getSurgeryHistoryList(), customer);
				break;
			case 12:
				updateDiseaseHistories(updateCustomerInformationRequest.getDiseaseHistoryList(), customer);
				break;
			default:
				break;
		}
	}

	private void updateAccidentHistories(List<UpdateAccidentHistoryRequest> accidentHistoryList, Customer customer) {
		if (accidentHistoryList != null) {
			for (UpdateAccidentHistoryRequest dto : accidentHistoryList) {
				try {
					// ID가 0 이하인 경우 무시 (업데이트만 수행)
					if (dto.getId() <= 0) {
						System.err.println("유효하지 않은 ID로 인해 사고 이력 업데이트가 무시되었습니다: " + dto);
						continue;
					}
					// 기존 사고 이력을 ID로 조회
					AccidentHistory accidentHistory = accidentHistoryEntityModel.getById(dto.getId());
					if (accidentHistory == null) {
						System.err.println("ID로 사고 이력을 찾을 수 없습니다: " + dto.getId());
						continue;
					}
					if (accidentHistory.getCustomerID() != customer.getId()) {
						System.err.println("해당 고객의 사고 이력이 아닙니다: " + dto.getId());
						continue; // 해당 이력 무시
					}
					// 날짜 값이 유효한 경우 변환
					Date date = null;
					if (dto.getDate() != null && !dto.getDate().isEmpty()) {
						LocalDate localDate = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					}
					// 기존 사고 이력 업데이트
					if (date != null) {
						accidentHistory.setDate(date);
					}
					accidentHistory.setAccidentDetail(dto.getAccidentDetail());
					accidentHistoryEntityModel.update(accidentHistory);
				} catch (Exception e) {
					System.err.println("사고 이력 업데이트 중 오류 발생: " + e.getMessage());
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
						System.err.println("유효하지 않은 ID로 인해 수술 이력 업데이트가 무시되었습니다: " + dto);
						continue;
					}
					// 기존 수술 이력을 ID로 조회
					SurgeryHistory surgeryHistory = surgeryHistoryEntityModel.getById(dto.getId());
					if (surgeryHistory == null) {
						System.err.println("ID로 수술 이력을 찾을 수 없습니다: " + dto.getId());
						continue;
					}
					// 고객 소유권 확인
					if (surgeryHistory.getCustomerID() != customer.getId()) {
						System.err.println("해당 고객의 수술 이력이 아닙니다: " + dto.getId());
						continue; // 해당 이력 무시
					}
					// 날짜 값이 유효한 경우 변환
					Date date = null;
					if (dto.getDate() != null && !dto.getDate().isEmpty()) {
						LocalDate localDate = LocalDate.parse(dto.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
					System.err.println("수술 이력 업데이트 중 오류 발생: " + e.getMessage());
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
						System.err.println("유효하지 않은 ID로 인해 병력 업데이트가 무시되었습니다: " + dto);
						continue;
					}
					// 기존 병력을 ID로 조회
					DiseaseHistory diseaseHistory = diseaseHistoryEntityModel.getById(dto.getId());
					if (diseaseHistory == null) {
						System.err.println("ID로 병력을 찾을 수 없습니다: " + dto.getId());
						continue;
					}
					// 고객 소유권 확인
					if (diseaseHistory.getCustomer_id() != customer.getId()) {
						System.err.println("해당 고객의 병력이 아닙니다: " + dto.getId());
						continue; // 해당 이력 무시
					}
					// 날짜 값이 유효한 경우 변환
					Date dateOfDiagnosis = null;
					if (dto.getDateOfDiagnosis() != null && !dto.getDateOfDiagnosis().isEmpty()) {
						LocalDate localDate = LocalDate.parse(dto.getDateOfDiagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
						dateOfDiagnosis = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					}
					// 기존 병력 업데이트
					if (dateOfDiagnosis != null) {
						diseaseHistory.setDate_of_diagnosis(dateOfDiagnosis); // 유효한 날짜만 설정
					}
					diseaseHistory.setName(dto.getName());
					diseaseHistoryEntityModel.update(diseaseHistory); // 업데이트 호출

				} catch (Exception e) {
					System.err.println("병력 업데이트 중 오류 발생: " + e.getMessage());
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
