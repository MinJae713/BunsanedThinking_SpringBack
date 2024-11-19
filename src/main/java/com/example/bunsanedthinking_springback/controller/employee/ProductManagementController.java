package com.example.bunsanedthinking_springback.controller.employee;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddAutomobileInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddDiseaseInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddInjuryInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.GetAllResponseResponse;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.global.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.productManagement.ProductManagementService;

@RestController
@RequestMapping("/employee/productManagement")
public class ProductManagementController {
	@Autowired
	private ProductManagementService productManagementSModel;

	@PostMapping("/addDiseaseInsurance")
	public void addDiseaseInsurance(@RequestBody AddDiseaseInsuranceRequest addDiseaseInsuranceRequest) throws DuplicateInsuranceException {
		productManagementSModel.addDiseaseInsurance(addDiseaseInsuranceRequest);
	}


	@PostMapping("/addInjuryInsurance")
	public void addInjuryInsurance(@RequestBody AddInjuryInsuranceRequest addInjuryInsuranceRequest) throws DuplicateInsuranceException {
		productManagementSModel.addInjuryInsurance(addInjuryInsuranceRequest);
	}

	@PostMapping("/addAutomobileInsurance")
	public void addAutomobileInsurance(@RequestBody AddAutomobileInsuranceRequest addAutomobileInsuranceRequest) throws DuplicateInsuranceException {
		productManagementSModel.addAutomobileInsurance(addAutomobileInsuranceRequest);
	}

	@DeleteMapping("/deleteInsuranceProduct")
	public void deleteInsuranceProduct(int id){
		productManagementSModel.deleteInsuranceProduct(id);
	}

	@GetMapping("/getInsuranceProduct")
	public Insurance getInsuranceProduct(int id) throws NotExistException {
		return productManagementSModel.getInsuranceProduct(id);
	}

	@PatchMapping("/updateDiseaseInsurance")
	public void updateDiseaseInsurance(int index, String input,int id) throws DuplicateInsuranceException{
		productManagementSModel.updateDiseaseInsurance(index, input, id);
	}

	@PatchMapping("/updateInjuryInsurance")
	public void updateInjuryInsurance(int index, String input,int id) throws DuplicateInsuranceException{
		productManagementSModel.updateInjuryInsurance(index, input, id);
	}

	@PatchMapping("/updateAutomobileInsurance")
	public void updateAutomobileInsurance(@RequestParam int index,@RequestParam String input,@RequestParam int id,@RequestBody(required = false) ArrayList<ServiceType> serviceTypeList) throws DuplicateInsuranceException{
		productManagementSModel.updateAutomobileInsurance(index, input, id, serviceTypeList);
	}

	@GetMapping("/getAll")
	public List<GetAllResponseResponse> getAll(){
		return productManagementSModel.getAll();
	}
}
