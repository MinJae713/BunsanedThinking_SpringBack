package com.example.bunsanedthinking_springback.controller.employee;

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

import java.util.List;

@RestController
@RequestMapping("/employee/customerInformationManagement")
public class CustomerInformationManagementController {

	@Autowired
	private CustomerInformationManagementSModel customerInformationManagementSModel;

	@PostMapping("/addCustomerInformation")
	public void addCustomerInformation(@RequestBody AddCustomerInformationDTO addCustomerInformationDTO) throws DuplicateResidentRegistrationNumberException {
		customerInformationManagementSModel.addCustomerInformation(
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
		customerInformationManagementSModel.deleteCustomerInformation(id);
	}

	@GetMapping("/getCustomerInformation")
	public CustomerVO getCustomerInformation(@RequestParam int id) throws NotExistException{
		return customerInformationManagementSModel.getCustomerInformation(id);
	}

	@PatchMapping("/updateCustomerInformation")
	public void updateCustomerInformation(@RequestBody UpdateCustomerInformationDTO updateCustomerInformationDTO) throws DuplicateResidentRegistrationNumberException, NotExistException{
		customerInformationManagementModel.updateCustomerInformation(updateCustomerInformationDTO);
	}

	@GetMapping("/getAll")
	public List<CustomerVO> getAll() {
		return customerInformationManagementSModel.getAll();
	}
}
