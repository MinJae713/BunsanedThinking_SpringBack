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
import com.example.bunsanedthinking_springback.model.entityModel.accidentHistory.AccidentHistoryDModel;
import com.example.bunsanedthinking_springback.model.entityModel.customer.CustomerDModel;
import com.example.bunsanedthinking_springback.model.entityModel.diseaseHistory.DiseaseHistoryDModel;
import com.example.bunsanedthinking_springback.model.entityModel.surgeryHistory.SurgeryHistoryEntityModel;
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
public class CustomerInformationManagementService {

	@Autowired
	private CustomerDModel customerDModel;
	@Autowired
	private AccidentHistoryDModel accidentHistoryDModel;
	@Autowired
	private SurgeryHistoryEntityModel surgeryHistoryEntityModel;
	@Autowired
	private DiseaseHistoryDModel diseaseHistoryDModel;
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
				//customer.setGender(Integer.parseInt(input)); 이거는 int타입
				customer.setGender(Gender.fromInt(Integer.parseInt(input))); //이거는 enum타입 둘 중에 어떤게 맞는걸까요? 일단 이렇게 해놓겠습니다
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
			case 11:
				customer.setBankName(input);
				customerDModel.update(customer);
				break;
			case 12:
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
