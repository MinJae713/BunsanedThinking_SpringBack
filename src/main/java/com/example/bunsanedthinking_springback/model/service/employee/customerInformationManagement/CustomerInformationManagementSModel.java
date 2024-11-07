package com.example.bunsanedthinking_springback.model.service.employee.customerInformationManagement;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AccidentHistoryMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseHistoryMapper;
import com.example.bunsanedthinking_springback.repository.SurgeryHistoryMapper;
import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;
import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerInformationManagementSModel {

	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private AccidentHistoryMapper accidentHistoryMapper;
	@Autowired
	private SurgeryHistoryMapper surgeryHistoryMapper;
	@Autowired
	private DiseaseHistoryMapper diseaseHistoryMapper;

	public void addCustomerInformation(String name, String phoneNumber, String job, int age, int gender,
		String residentRegistrationNumber, String address, long property,
		List<AccidentHistory> tempAccidentHistoryList,
		List<SurgeryHistory> tempSurgeryHistoryList,
		List<DiseaseHistory> tempDiseaseHistoryList,
		String bankName, String bankAccount) throws DuplicateResidentRegistrationNumberException {
		if (customerMapper.findByResidentRegistrationNumber_CustomerInformationManagement(residentRegistrationNumber)
			!= null) {
			throw new DuplicateResidentRegistrationNumberException();
		}

		// VO 객체 생성
		CustomerVO customerVO = new CustomerVO();
		customerVO.setName(name);
		customerVO.setPhone_number(phoneNumber);
		customerVO.setJob(job);
		customerVO.setAge(age);
		customerVO.setGender(gender); // Gender enum을 int로 저장
		customerVO.setResident_registration_number(residentRegistrationNumber);
		customerVO.setAddress(address);
		customerVO.setProperty(property);
		customerVO.setBank_name(bankName);
		customerVO.setBank_account(bankAccount);

		// VO를 DB에 추가
		customerMapper.insert_CustomerInformationManagement(customerVO);

		if (tempAccidentHistoryList != null) {
			for (AccidentHistory accident : tempAccidentHistoryList) {
				AccidentHistoryVO accidentHistoryVO = accident.getaccidentVO();
				accident.setCustomerID(customerVO.getId());
				accidentHistoryMapper.insert_accidentHistory_CustomerInformationManagement(accidentHistoryVO);
			}
		}
		if (tempSurgeryHistoryList != null) {
			for (SurgeryHistory surgery : tempSurgeryHistoryList) {
				SurgeryHistoryVO surgeryHistoryVO = surgery.getsurgeryVO();
				surgery.setCustomerID(customerVO.getId());
				surgeryHistoryMapper.insert_surgeryHistory_CustomerInformationManagement(surgeryHistoryVO);
			}
		}
		if (tempDiseaseHistoryList != null) {
			for (DiseaseHistory disease : tempDiseaseHistoryList) {
				DiseaseHistoryVO diseaseHistoryVO = disease.getdiseaseVO();
				disease.setCustomer_id(customerVO.getId());
				diseaseHistoryMapper.insert_diseaseHistory_CustomerInformationManagement(diseaseHistoryVO);
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

	public CustomerVO getCustomerInformation(int id) throws NotExistException {
		CustomerVO customerVO = customerMapper.findById_CustomerInformationManagement(id);
		if (customerVO == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		return customerVO;
	}

	public void updateCustomerInformation(int index, String input, int id) throws NotExistException {
		CustomerVO customerVO = getCustomerInformation(id);

		switch (index) {
			case 1:
				customerVO.setName(input);
				break;
			case 2:
				customerVO.setPhone_number(input);
				break;
			case 3:
				customerVO.setJob(input);
				break;
			case 4:
				customerVO.setAge(Integer.parseInt(input));
				break;
			case 5:
				if ("Male".equalsIgnoreCase(input)) {
					customerVO.setGender(Gender.Male.ordinal());
				} else if ("Female".equalsIgnoreCase(input)) {
					customerVO.setGender(Gender.Female.ordinal());
				}
				break;
			case 6:
				customerVO.setAddress(input);
				break;
			case 7:
				customerVO.setProperty(Long.parseLong(input));
				break;
			case 11:
				customerVO.setBank_name(input);
				break;
			case 12:
				customerVO.setBank_account(input);
				break;
			default:
				throw new IllegalArgumentException("유효하지 않은 선택입니다. 올바른 값을 입력하세요.");
		}
		customerMapper.update_CustomerInformationManagement(customerVO);
	}

	public List<CustomerVO> getAll() {
		return customerMapper.getAll_CustomerInformationManagement();
	}
}
