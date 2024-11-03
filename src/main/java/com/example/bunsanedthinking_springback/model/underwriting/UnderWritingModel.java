package com.example.bunsanedthinking_springback.model.underwriting;

import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.contract.ContractList;
import com.example.bunsanedthinking_springback.entity.contract.ContractStatus;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistoryList;
import com.example.bunsanedthinking_springback.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AccidentHistoryMapper;
import com.example.bunsanedthinking_springback.repository.AccidentMapper;
import com.example.bunsanedthinking_springback.repository.ContractMapper;
import com.example.bunsanedthinking_springback.repository.CustomerMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseHistoryMapper;
import com.example.bunsanedthinking_springback.repository.SurgeryHistoryMapper;
import com.example.bunsanedthinking_springback.vo.AccidentHistoryVO;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import com.example.bunsanedthinking_springback.vo.DiseaseHistoryVO;
import com.example.bunsanedthinking_springback.vo.SurgeryHistoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UnderWritingModel {

	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	AccidentHistoryMapper accidentHistoryMapper;
	@Autowired
	SurgeryHistoryMapper surgeryHistoryMapper;
	@Autowired
	DiseaseHistoryMapper diseaseHistoryMapper;


	public void applyCoperation() {

	}

	public void applyReinsurance() {

	}

	public boolean reviewAcquisition(Contract contract, boolean result, ContractList contractList)
			throws AlreadyProcessedException, NotExistContractException {
		if (contract.getContractStatus() != ContractStatus.ContractRequesting) {
			throw new AlreadyProcessedException();
		}

		contract.review(result, contractList);
		return result;
	}
	public ArrayList<Contract> getAllRequestingInsurance(ContractList contractList){
		return contractList.getAllRequestingInsurance();
	}
	public Customer get(CustomerList customerList, int id) throws NotExistException {

		CustomerVO customerVO = customerMapper.get_UnderWritingModel(id);
		Customer customer = new Customer();
		customer.setId(customerVO. getId());
		customer.setName(customerVO.getName());
		customer.setPhoneNumber(customerVO.getPhone_number());
		customer.setJob(customerVO.getJob());
		customer.setAge(customerVO.getAge());
		customer.setGender(Gender.fromInt(customerVO.getGender()));
		customer.setResidentRegistrationNumber(customerVO.getResident_registration_number());
		customer.setAddress(customerVO.getAddress());
		customer.setBankAccount(customerVO.getBank_account());
		customer.setBankName(customerVO.getBank_name());

		ArrayList<SurgeryHistoryVO> surgeryHistoryVOs = surgeryHistoryMapper.get_UnderWritingModel(id);
		ArrayList<SurgeryHistory> surgeryHistoryList = new ArrayList<>();
		for(SurgeryHistoryVO surgeryHistoryVO : surgeryHistoryVOs) {
			SurgeryHistory surgeryHistory = new SurgeryHistory();
			surgeryHistory.setId(surgeryHistoryVO.getId());
			surgeryHistory.setHospitalName(surgeryHistoryVO.getHospital_name());
			surgeryHistory.setName(surgeryHistoryVO.getName());
			surgeryHistory.setCustomerID(surgeryHistoryVO.getCustomer_id());
			surgeryHistory.setDate(Date.from(surgeryHistoryVO.getDate().atZone(ZoneId.systemDefault()).toInstant()));
			surgeryHistoryList.add(surgeryHistory);
		}
		customer.setSurgeryHistoryList(surgeryHistoryList);

		ArrayList<AccidentHistoryVO> accidentHistoryVOs = accidentHistoryMapper.get_UnderWritingModel(id);
		ArrayList<AccidentHistory> accidentHistoryList = new ArrayList<>();
		for(AccidentHistoryVO accidentHistoryVO :accidentHistoryVOs){
			AccidentHistory accidentHistory = new AccidentHistory();
			accidentHistory.setId(accidentHistoryVO.getId());
			accidentHistory.setDate(Date.from(accidentHistoryVO.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			accidentHistory.setAccidentDetail(accidentHistoryVO.getDetails_of_accident());
			accidentHistory.setCustomerID(accidentHistoryVO.getCustomer_id());
		}
		customer.setAccidentHistoryList(accidentHistoryList);

		ArrayList<DiseaseHistoryVO> diseaseHistoryVOs = diseaseHistoryMapper.get_UnderWritingModel(id);
		ArrayList<DiseaseHistory> diseaseHistoryList = new ArrayList<>();
		for(DiseaseHistoryVO diseaseHistoryVO: diseaseHistoryVOs){
			DiseaseHistory diseaseHistory = new DiseaseHistory();
			diseaseHistory.setId(diseaseHistoryVO.getId());
			diseaseHistory.setDate_of_diagnosis(Date.from(diseaseHistoryVO.getDate_of_diagnosis().atStartOfDay(ZoneId.systemDefault()).toInstant()));
			diseaseHistory.setName(diseaseHistoryVO.getName());
			diseaseHistory.setCustomer_id(diseaseHistoryVO.getCustomer_id());
			diseaseHistoryList.add(diseaseHistory);
		}
		customer.setDiseaseHistoryList(diseaseHistoryList);

		return customer;
		// return customerList.get(id);
	}
	public Contract get(ContractList contractList, int id) throws NotExistContractException {
		return contractList.get(id);
	}
	public ArrayList<Contract> getAllNotRequestingInsurance(ContractList contractList){
		return contractList.getAllNotRequestingInsurance();
	}
}