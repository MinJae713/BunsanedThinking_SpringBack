package com.example.bunsanedthinking_springback.controller;

import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.productManagement.ProductManagementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/employee/productManagement")
public class ProductManagementController {
	@Autowired
	private ProductManagementModel productManagementModel;

	// public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
	// 								int contractPeriod, String diseaseName, int diseaseLimit, int surgeriesLimit, ProductList productList) throws DuplicateInsuranceException {
	// 	productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage, monthlyPremium,
	// 			contractPeriod, diseaseName, diseaseLimit, surgeriesLimit, productList);
	// }
	//
	// public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
	// 								int contractPeriod, InjuryType injuryType, int surgeriesLimit, ProductList productList) throws DuplicateInsuranceException {
	// 	productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage,
	// 			monthlyPremium, contractPeriod, injuryType, surgeriesLimit, productList);
	// }
	//
	// public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
	// 								int contractPeriod, int accidentLimit, VehicleType vehicleType, ArrayList<ServiceType> serviceTypeList,
	// 								ProductList productList) throws DuplicateInsuranceException {
	// 	productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage, monthlyPremium,
	// 			contractPeriod, accidentLimit, vehicleType, serviceTypeList, productList);
	// }
	//
	// public void deleteInsuranceProduct(ProductList productList, int id) throws NotExistException {
	// 	productManagementModel.deleteInsuranceProduct(productList, id);
	// }
	//
	// public Insurance getInsuranceProduct(ProductList productList, int id) throws NotExistException {
	// 	return productManagementModel.getInsuranceProduct(productList, id);
	// }
	//
	// public void updateInsuranceProduct(int index, String input, Disease diseaseInsurance, ProductList productList) throws DuplicateInsuranceException, NotExistException {
	// 	productManagementModel.updateInsuranceProduct(index, input, diseaseInsurance, productList);
	// }
	//
	// public void updateInsuranceProduct(int index, String input, Injury injuryInsurance, ProductList productList) throws DuplicateInsuranceException, NotExistException {
	// 	productManagementModel.updateInsuranceProduct(index, input, injuryInsurance, productList);
	// }
	//
	// public void updateInsuranceProduct(int index, String input, Automobile automobileInsurance, ArrayList<ServiceType> serviceTypeList, ProductList productList) throws DuplicateInsuranceException, NotExistException {
	// 	productManagementModel.updateInsuranceProduct(index, input, automobileInsurance, serviceTypeList, productList);
	// }
	// public ArrayList<Product> getAll(ProductList productList){
	// 	return productManagementModel.getAll(productList);
	// }



	public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
		int contractPeriod, String diseaseName, int diseaseLimit, int surgeriesLimit, ProductList productList) throws DuplicateInsuranceException {
		productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage, monthlyPremium,
			contractPeriod, diseaseName, diseaseLimit, surgeriesLimit, productList);
	}

	public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
		int contractPeriod, InjuryType injuryType, int surgeriesLimit, ProductList productList) throws DuplicateInsuranceException {
		productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage,
			monthlyPremium, contractPeriod, injuryType, surgeriesLimit, productList);
	}

	public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
		int contractPeriod, int accidentLimit, VehicleType vehicleType, ArrayList<ServiceType> serviceTypeList,
		ProductList productList) throws DuplicateInsuranceException {
		productManagementModel.addInsuranceProduct(insuranceType, name, limit, ageRange, coverage, monthlyPremium,
			contractPeriod, accidentLimit, vehicleType, serviceTypeList, productList);
	}

	@DeleteMapping("/deleteInsuranceProduct")
	public void deleteInsuranceProduct(int id){
		productManagementModel.deleteInsuranceProduct(id);
	}

	@GetMapping("/getInsuranceProduct")
	public Insurance getInsuranceProduct(int id) throws NotExistException {
		return productManagementModel.getInsuranceProduct(id);
	}

	public void updateInsuranceProduct(int index, String input, Disease diseaseInsurance, ProductList productList) throws DuplicateInsuranceException, NotExistException {
		productManagementModel.updateInsuranceProduct(index, input, diseaseInsurance, productList);
	}

	public void updateInsuranceProduct(int index, String input, Injury injuryInsurance, ProductList productList) throws DuplicateInsuranceException, NotExistException {
		productManagementModel.updateInsuranceProduct(index, input, injuryInsurance, productList);
	}

	public void updateInsuranceProduct(int index, String input, Automobile automobileInsurance, ArrayList<ServiceType> serviceTypeList, ProductList productList) throws DuplicateInsuranceException, NotExistException {
		productManagementModel.updateInsuranceProduct(index, input, automobileInsurance, serviceTypeList, productList);
	}

	@GetMapping("/getAll")
	public ArrayList<Product> getAll(){
		return productManagementModel.getAll();
	}
}
