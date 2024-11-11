package com.example.bunsanedthinking_springback.controller.employee;

import com.example.bunsanedthinking_springback.dto.employee.productManagement.AutomobileDTO;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.DiseaseDTO;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.InjuryDTO;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.model.service.employee.productManagement.ProductManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import com.example.bunsanedthinking_springback.global.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;

@RestController
@RequestMapping("/employee/productManagement")
public class ProductManagementController {
	@Autowired
	private ProductManagementService productManagementSModel;

	@PostMapping("/addDiseaseInsurance")
	public void addDiseaseInsurance(@RequestBody DiseaseDTO diseaseDTO) throws DuplicateInsuranceException {
		productManagementSModel.addDiseaseInsurance(diseaseDTO);
	}

	@PostMapping("/addInjuryInsurance")
	public void addInjuryInsurance(@RequestBody InjuryDTO injuryDTO) throws DuplicateInsuranceException {
		productManagementSModel.addInjuryInsurance(injuryDTO);
	}

	@PostMapping("/addAutomobileInsurance")
	public void addAutomobileInsurance(@RequestBody AutomobileDTO automobileDTO) throws DuplicateInsuranceException {
		productManagementSModel.addAutomobileInsurance(automobileDTO);
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
	public void updateAutomobileInsurance(int index, String input, int id, ArrayList<ServiceType> serviceTypeList) throws DuplicateInsuranceException{
		productManagementSModel.updateAutomobileInsurance(index, input, id, serviceTypeList);
	}

	@GetMapping("/getAll")
	public ArrayList<Product> getAll(){
		return productManagementSModel.getAll();
	}
}
