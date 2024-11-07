package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.dto.mo.AddCustomerInformationDTO;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.customerInformationManagement.CustomerInformationManagementModel;
import com.example.bunsanedthinking_springback.vo.CustomerVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/customerInformationManagement")
public class CustomerInformationManagementController {

	@Autowired
	private CustomerInformationManagementModel customerInformationManagementModel;

	@PostMapping("/addCustomerInformation")
	public void addCustomerInformation(@RequestBody AddCustomerInformationDTO addCustomerInformationDTO) throws
		DuplicateResidentRegistrationNumberException {
		customerInformationManagementModel.addCustomerInformation(
			addCustomerInformationDTO.getName(),
			addCustomerInformationDTO.getPhoneNumber(),
			addCustomerInformationDTO.getJob(),
			addCustomerInformationDTO.getAge(),
			addCustomerInformationDTO.getGender(),
			addCustomerInformationDTO.getResidentRegistrationNumber(),
			addCustomerInformationDTO.getAddress(),
			addCustomerInformationDTO.getProperty(),
			addCustomerInformationDTO.getTempAccidentHistoryList(),
			addCustomerInformationDTO.getTempSurgeryHistoryList(),
			addCustomerInformationDTO.getTempDiseaseHistoryList(),
			addCustomerInformationDTO.getBankName(),
			addCustomerInformationDTO.getBankAccount()
				/*name, phoneNumber, job, age, gender,
				residentRegistrationNumber, address, property, tempAccidentHistoryList, tempSurgeryHistoryList, 
				tempDiseaseHistoryList, bankName, bankAccount*/);
		// 이 부분도 RequestBody 물어보기
	}

	@DeleteMapping("/deleteCustomerInformation")
	public void deleteCustomerInformation(@RequestParam int id) throws NotExistException {
		customerInformationManagementModel.deleteCustomerInformation(id);
	}

	@GetMapping("/getCustomerInformation")
	public CustomerVO getCustomerInformation(@RequestParam int id) throws NotExistException {
		return customerInformationManagementModel.getCustomerInformation(id);
	}

	@PatchMapping("updateCustomerInformation")
	public void updateCustomerInformation(@RequestParam int index, @RequestParam String input,
		@RequestParam int id) throws DuplicateResidentRegistrationNumberException, NotExistException {
		customerInformationManagementModel.updateCustomerInformation(index, input, id);
	}

	@GetMapping("/getAll")
	public List<CustomerVO> getAll() {
		return customerInformationManagementModel.getAll();
	}
}
