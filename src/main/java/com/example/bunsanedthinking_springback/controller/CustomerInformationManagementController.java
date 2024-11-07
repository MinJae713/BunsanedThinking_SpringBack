package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.dto.mo.AddCustomerInformationDTO;
import com.example.bunsanedthinking_springback.dto.mo.UpdateCustomerInformationDTO;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistory;
import com.example.bunsanedthinking_springback.entity.accidentHistory.AccidentHistoryList;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.customer.CustomerList;
import com.example.bunsanedthinking_springback.entity.customer.Gender;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistoryList;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistory;
import com.example.bunsanedthinking_springback.entity.surgeryHistory.SurgeryHistoryList;
import com.example.bunsanedthinking_springback.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.customerInformationManagement.CustomerInformationManagementModel;
import com.example.bunsanedthinking_springback.vo.CustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee/customerInformationManagement")
public class CustomerInformationManagementController {

	@Autowired
	private CustomerInformationManagementModel customerInformationManagementModel;

	@PostMapping("/addCustomerInformation")
	public void addCustomerInformation(@RequestBody AddCustomerInformationDTO addCustomerInformationDTO) throws DuplicateResidentRegistrationNumberException {
		customerInformationManagementModel.addCustomerInformation(addCustomerInformationDTO);
	}

	@DeleteMapping("/deleteCustomerInformation")
	public void deleteCustomerInformation(@RequestParam int id) throws NotExistException {
		customerInformationManagementModel.deleteCustomerInformation(id);
	}

	@GetMapping("/getCustomerInformation")
	public CustomerVO getCustomerInformation(@RequestParam int id) throws NotExistException{
		return customerInformationManagementModel.getCustomerInformation(id);
	}

	@PatchMapping("updateCustomerInformation")
	public void updateCustomerInformation(@RequestBody UpdateCustomerInformationDTO updateCustomerInformationDTO) throws DuplicateResidentRegistrationNumberException, NotExistException{
		customerInformationManagementModel.updateCustomerInformation(
				updateCustomerInformationDTO.getId(),
				updateCustomerInformationDTO.getName(),
				updateCustomerInformationDTO.getPhoneNumber(),
				updateCustomerInformationDTO.getJob(),
				updateCustomerInformationDTO.getAge(),
				updateCustomerInformationDTO.getGender(),
				updateCustomerInformationDTO.getResidentRegistrationNumber(),
				updateCustomerInformationDTO.getAddress(),
				updateCustomerInformationDTO.getProperty(),
				updateCustomerInformationDTO.getAccidentHistoryList(),
				updateCustomerInformationDTO.getSurgeryHistoryList(),
				updateCustomerInformationDTO.getDiseaseHistoryList(),
				updateCustomerInformationDTO.getBankName(),
				updateCustomerInformationDTO.getBankAccount()
		);
	}

	@GetMapping("/getAll")
	public List<CustomerVO> getAll() {
		return customerInformationManagementModel.getAll();
	}
}
