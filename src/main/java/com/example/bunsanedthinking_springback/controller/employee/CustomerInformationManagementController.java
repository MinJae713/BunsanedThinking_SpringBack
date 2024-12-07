package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request.AddCustomerInformationRequest;
import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.request.UpdateCustomerInformationRequest;
import com.example.bunsanedthinking_springback.dto.employee.customerInformationManagement.response.CustomerResponse;
import com.example.bunsanedthinking_springback.global.exception.DuplicateResidentRegistrationNumberException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.customerInformationManagement.CustomerInformationManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/customerInformationManagement")
public class CustomerInformationManagementController {

	@Autowired
	private CustomerInformationManagementService customerInformationManagementSModel;

	@PostMapping("/addCustomerInformation")
	public void addCustomerInformation(@Valid @RequestBody AddCustomerInformationRequest addCustomerInformationRequest) throws DuplicateResidentRegistrationNumberException {
		customerInformationManagementSModel.addCustomerInformation(addCustomerInformationRequest);
	}

	@DeleteMapping("/deleteCustomerInformation")
	public void deleteCustomerInformation(@RequestParam int id) throws NotExistException {
		customerInformationManagementSModel.deleteCustomerInformation(id);
	}

	@GetMapping("/getCustomerInformation")
	public CustomerResponse getCustomerInformation(@RequestParam int id) throws NotExistException{
		return customerInformationManagementSModel.getCustomerInformation(id);
	}

	@PatchMapping("/updateCustomerInformation")
	public void updateCustomerInformation(@Valid @RequestBody UpdateCustomerInformationRequest updateCustomerInformationRequest) throws DuplicateResidentRegistrationNumberException, NotExistException{
		customerInformationManagementSModel.updateCustomerInformation(updateCustomerInformationRequest);
	}

	@GetMapping("/getAll")
	public List<CustomerResponse> getAll() {
		return customerInformationManagementSModel.getAll();
	}
}
