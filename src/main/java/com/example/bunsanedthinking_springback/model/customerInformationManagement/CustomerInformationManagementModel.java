package com.example.bunsanedthinking_springback.model.customerInformationManagement;


import com.example.bunsanedthinking_springback.dto.dae.AccidentHistoryDTO;
import com.example.bunsanedthinking_springback.dto.dae.DiseaseHistoryDTO;
import com.example.bunsanedthinking_springback.dto.dae.SurgeryHistoryDTO;
import com.example.bunsanedthinking_springback.dto.mo.AddCustomerInformationDTO;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.department.Department;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	public void addCustomerInformation(AddCustomerInformationDTO addCustomerInformationDTO) throws DuplicateResidentRegistrationNumberException {
		if (customerMapper.findByResidentRegistrationNumber_CustomerInformationManagement(addCustomerInformationDTO.getResidentRegistrationNumber()) != null) {
			throw new DuplicateResidentRegistrationNumberException();
		}

		Integer maxId = customerMapper.getMaxId_CustomerInformationManagement();
		int id;
		if (maxId == null) {
			id = Integer.parseInt(Customer.CUSTOMER_SERIAL_NUMBER + "1");
		} else {
			String index = (maxId + "").substring((Customer.CUSTOMER_SERIAL_NUMBER + "").length());
			id = Integer.parseInt((Customer.CUSTOMER_SERIAL_NUMBER + "") + (Integer.parseInt(index) + 1));
		}

		CustomerVO customerVO = new CustomerVO(
				id,
				addCustomerInformationDTO.getAddress(),
				addCustomerInformationDTO.getAge(),
				addCustomerInformationDTO.getBankAccount(),
				addCustomerInformationDTO.getBankName(),
				addCustomerInformationDTO.getGender(),
				addCustomerInformationDTO.getJob(),
				addCustomerInformationDTO.getName(),
				addCustomerInformationDTO.getPhoneNumber(),
				addCustomerInformationDTO.getProperty(),
				addCustomerInformationDTO.getResidentRegistrationNumber()
				);
		// VO를 DB에 추가
		customerMapper.insert_CustomerInformationManagement(customerVO);

		if(addCustomerInformationDTO.getAccidentHistoryList() != null) {
			Integer accidentHistoryMaxId = accidentHistoryMapper.getMaxId_CustomerInformationManagement();
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
			for(AccidentHistoryDTO e : addCustomerInformationDTO.getAccidentHistoryList()) {
				LocalDate date = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				AccidentHistoryVO accidentHistoryVO = new AccidentHistoryVO(accidentHistoryId, date,
						e.getAccidentDetail(), id);
				accidentHistoryMapper.insert_SalesModel(accidentHistoryVO);
				maxIndex++;
				accidentHistoryId = Integer.parseInt((AccidentHistory.ACCIDENT_HISTORY_SERIAL_NUMBER+ "") + maxIndex);

			}
		}

		if(addCustomerInformationDTO.getSurgeryHistoryList() != null) {
			Integer surgeryHistoryMaxId = surgeryHistoryMapper.getMaxId_CustomerInformationManagement();
			int surgeryHistoryId;
			int maxIndex;
			if (surgeryHistoryMaxId == null) {
				surgeryHistoryId = Integer.parseInt(SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else{
				String index = (surgeryHistoryMaxId + "").substring((SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER+ "").length());
				maxIndex = Integer.parseInt(index) + 1;
				surgeryHistoryId = Integer.parseInt((SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER+ "") + maxIndex);
			}
			for(SurgeryHistoryDTO e : addCustomerInformationDTO.getSurgeryHistoryList()) {
				LocalDate date = LocalDate.parse(e.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				SurgeryHistoryVO surgeryHistoryVO = new SurgeryHistoryVO(surgeryHistoryId, e.getHospitalName(),
						e.getName(), date, id);
				surgeryHistoryMapper.insert_SalesModel(surgeryHistoryVO);
				maxIndex++;
				surgeryHistoryId = Integer.parseInt((SurgeryHistory.SURGERYHISTORY_SERIAL_NUMBER+ "") + maxIndex);
			}
		}

		if(addCustomerInformationDTO.getDiseaseHistoryList() != null) {
			Integer diseaseHistoryMaxId = diseaseHistoryMapper.getMaxId_CustomerInformationManagement();
			int diseaseHistoryId;
			int maxIndex;
			if (diseaseHistoryMaxId == null) {
				diseaseHistoryId = Integer.parseInt(DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER + "1");
				maxIndex = 1;
			} else {
				String index = (diseaseHistoryMaxId + "").substring((DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER+ "").length());
				maxIndex = Integer.parseInt(index) + 1;
				diseaseHistoryId = Integer.parseInt((DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER+ "") + maxIndex);
			}
			for(DiseaseHistoryDTO e : addCustomerInformationDTO.getDiseaseHistoryList()) {
				LocalDate date = LocalDate.parse(e.getDate_of_diagnosis(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				DiseaseHistoryVO diseaseHistoryVO = new DiseaseHistoryVO(diseaseHistoryId, date,
						e.getName(), id);
				diseaseHistoryMapper.insert_SalesModel(diseaseHistoryVO);
				maxIndex++;
				diseaseHistoryId = Integer.parseInt((DiseaseHistory.DISEASE_HISTORY_SERIAL_NUMBER+ "") + maxIndex);
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

	public CustomerVO getCustomerInformation(int id) throws NotExistException{
		CustomerVO customerVO = customerMapper.findById_CustomerInformationManagement(id);
		if (customerVO == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}
		return customerVO;
	}

	public void updateCustomerInformation(int id, String name, String phoneNumber, String job, int age, int gender,
										  String residentRegistrationNumber, String address, long property,
										  List<AccidentHistory> accidentHistoryList,
										  List<SurgeryHistory> surgeryHistoryList,
										  List<DiseaseHistory> diseaseHistoryList,
										  String bankName, String bankAccount) throws NotExistException{
		CustomerVO customerVO = customerMapper.findById_CustomerInformationManagement(id);
		if (customerVO == null) {
			throw new NotExistException("해당하는 고객 정보가 존재하지 않습니다.");
		}

		if (name != null) customerVO.setName(name);
		if (phoneNumber != null) customerVO.setPhone_number(phoneNumber);
		if (job != null) customerVO.setJob(job);
		if (age > 0) customerVO.setAge(age);
		if (gender >= 0) customerVO.setGender(gender);
		if (residentRegistrationNumber != null) customerVO.setResident_registration_number(residentRegistrationNumber);
		if (address != null) customerVO.setAddress(address);
		if (property > 0) customerVO.setProperty(property);
		if (bankName != null) customerVO.setBank_name(bankName);
		if (bankAccount != null) customerVO.setBank_account(bankAccount);

		customerMapper.update_CustomerInformationManagement(customerVO);

	}

	public List<CustomerVO> getAll() {
		return customerMapper.getAll_CustomerInformationManagement();
	}
}