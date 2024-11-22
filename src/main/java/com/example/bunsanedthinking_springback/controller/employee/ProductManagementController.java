package com.example.bunsanedthinking_springback.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddAutomobileInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddDiseaseInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddInjuryInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.UpdateAutomobileInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.UpdateDiseaseInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.UpdateInjuryInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductResponse;
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
	public ManageInsuranceProductResponse getInsuranceProduct(int id) throws NotExistException {
		return productManagementSModel.getInsuranceProduct(id);
	}

	@GetMapping("/getInsuranceProductDetail")
	public ManageInsuranceProductDetailResponse getInsuranceProductDetail(int id) throws NotExistException {
		return productManagementSModel.getInsuranceProductDetail(id);
	}

	@PatchMapping("/updateDiseaseInsurance")
	public void updateDiseaseInsurance(@RequestBody UpdateDiseaseInsuranceRequest updateDiseaseInsuranceRequest) throws DuplicateInsuranceException{
		productManagementSModel.updateDiseaseInsurance(updateDiseaseInsuranceRequest);
	}

	@PatchMapping("/updateInjuryInsurance")
	public void updateInjuryInsurance(@RequestBody UpdateInjuryInsuranceRequest updateInjuryInsuranceRequest) throws DuplicateInsuranceException{
		productManagementSModel.updateInjuryInsurance(updateInjuryInsuranceRequest);
	}

	@PatchMapping("/updateAutomobileInsurance")
	public void updateAutomobileInsurance(@RequestBody UpdateAutomobileInsuranceRequest updateAutomobileInsuranceRequest) throws DuplicateInsuranceException{
		productManagementSModel.updateAutomobileInsurance(updateAutomobileInsuranceRequest);
	}

	@GetMapping("/getAllInsurance")
	public List<ManageInsuranceProductResponse> getAllInsurance(){
		return productManagementSModel.getAllInsurance();
	}

	@GetMapping("/getAllinJuryInsurance")
	public List<ManageInsuranceProductResponse> getAllinJuryInsurance(){
		return productManagementSModel.getAllinJuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public List<ManageInsuranceProductResponse> getAllAutomobileInsurance(){
		return productManagementSModel.getAllAutomobileInsurance();
	}

	@GetMapping("/getAllDiseaseInsurance")
	public List<ManageInsuranceProductResponse> getAllDiseaseInsurance(){
		return productManagementSModel.getAllDiseaseInsurance();
	}
}
