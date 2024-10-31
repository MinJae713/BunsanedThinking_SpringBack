package com.example.bunsanedthinking_springback.model.productManagement;

import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-5-2024 占쏙옙占쏙옙 7:52:27
 */

@Service
public class ProductManagementModel {

	public static String serialNumber = "090";

	public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
									int contractPeriod, String diseaseName, int diseaseLimit, int surgeriesLimit, ProductList productList) throws DuplicateInsuranceException {
		for (Product product : productList.getAll()) {
			if (product.getName().equals(name)) {
				throw new DuplicateInsuranceException();
			}
		}
		Insurance diseaseInsurance = new Disease(insuranceType, name, limit, ageRange, coverage, monthlyPremium, contractPeriod,
				diseaseName, diseaseLimit, surgeriesLimit);
		productList.add(diseaseInsurance);
	}

	public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
									int contractPeriod, InjuryType injuryType, int surgeriesLimit, ProductList productList) throws DuplicateInsuranceException {
			for (Product product : productList.getAll()) {
				if (product.getName().equals(name)) {
					throw new DuplicateInsuranceException();
				}
			}
		Insurance injuryInsurance = new Injury(insuranceType, name, limit, ageRange, coverage, monthlyPremium, contractPeriod, injuryType,
				surgeriesLimit);
		productList.add(injuryInsurance);
	}

	public void addInsuranceProduct(InsuranceType insuranceType, String name, int limit, int ageRange, String coverage, int monthlyPremium,
			int contractPeriod, int accidentLimit, VehicleType vehicleType, ArrayList<ServiceType> serviceTypeList,
			ProductList productList) throws DuplicateInsuranceException {
		for (Product product : productList.getAll()) {
			if (product.getName().equals(name)) {
				throw new DuplicateInsuranceException();
			}
		}
		Insurance automobileInsurance = new Automobile(insuranceType, name, limit, ageRange, coverage, monthlyPremium, contractPeriod,
				accidentLimit, vehicleType, serviceTypeList);
		productList.add(automobileInsurance);
	}

	public void deleteInsuranceProduct(ProductList productList, int id) throws NotExistException {
		productList.delete(id);
	}

	public Insurance getInsuranceProduct(ProductList productList, int id) throws NotExistException {
		try {
			return (Insurance) productList.get(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 보험 상품 정보가 존재하지 않습니다.");
		}
	}

	public void updateInsuranceProduct(int index, String input, Disease diseaseInsurance, ProductList productList) throws DuplicateInsuranceException, NotExistException {
		
		switch (index) {
		case 1:
			for (Product product : productList.getAll()) {
				if (product.getName().equals(input)) {
					throw new DuplicateInsuranceException();
				}
			}
			diseaseInsurance.setName(input);
			productList.update(diseaseInsurance);
			break;
		case 2:
			diseaseInsurance.setAgeRange(Integer.parseInt(input));
			productList.update(diseaseInsurance);
			break;
		case 3:
			diseaseInsurance.setCoverage(input);
			productList.update(diseaseInsurance);
			break;
		case 4:
			diseaseInsurance.setMonthlyPremium(Integer.parseInt(input));
			productList.update(diseaseInsurance);
			break;
		case 5:
			int contractPeriod  = Integer.parseInt(input);
			diseaseInsurance.setContractPeriod(contractPeriod);
			productList.update(diseaseInsurance);
			break;
		case 6:
			diseaseInsurance.setDiseaseLimit(Integer.parseInt(input));
			productList.update(diseaseInsurance);
			break;
		case 7:
			diseaseInsurance.setDiseaseName(input);
			productList.update(diseaseInsurance);
			break;
		case 8:
			diseaseInsurance.setSurgeriesLimit(Integer.parseInt(input));
			productList.update(diseaseInsurance);
			break;
		default:
			break;
		}
	}

	public void updateInsuranceProduct(int index, String input, Injury injuryInsurance, ProductList productList) throws DuplicateInsuranceException, NotExistException {
		switch (index) {
		case 1:
			for (Product product : productList.getAll()) {
				if (product.getName().equals(input)) {
					throw new DuplicateInsuranceException();
				}
			}
			injuryInsurance.setName(input);
			productList.update(injuryInsurance);
			break;
		case 2:
			injuryInsurance.setAgeRange(Integer.parseInt(input));
			productList.update(injuryInsurance);
			break;
		case 3:
			injuryInsurance.setCoverage(input);
			productList.update(injuryInsurance);
			break;
		case 4:
			injuryInsurance.setMonthlyPremium(Integer.parseInt(input));
			productList.update(injuryInsurance);
			break;
		case 5:
			int contractPeriod = Integer.parseInt(input);
			injuryInsurance.setContractPeriod(contractPeriod);
			productList.update(injuryInsurance);
			break;
		case 6:
			if (InjuryType.Minor.ordinal() == (Integer.parseInt(input) - 1)) {
				injuryInsurance.setInjuryType(InjuryType.Minor);
			} else if (InjuryType.Serious.ordinal() == (Integer.parseInt(input) - 1)) {
				injuryInsurance.setInjuryType(InjuryType.Serious);
			}
			productList.update(injuryInsurance);
			break;
		case 7:
			injuryInsurance.setSurgeriesLimit(Integer.parseInt(input));
			productList.update(injuryInsurance);
			break;
		default:
			break;
		}
	}

	public void updateInsuranceProduct(int index, String input, Automobile automobileInsurance, ArrayList<ServiceType> serviceTypeList, ProductList productList) throws DuplicateInsuranceException, NotExistException {
		switch (index) {
		case 1:
			for (Product product : productList.getAll()) {
				if (product.getName().equals(input)) {
					throw new DuplicateInsuranceException();
				}
			}
			automobileInsurance.setName(input);
			productList.update(automobileInsurance);
			break;
		case 2:
			automobileInsurance.setAgeRange(Integer.parseInt(input));
			productList.update(automobileInsurance);
			break;
		case 3:
			automobileInsurance.setCoverage(input);
			productList.update(automobileInsurance);
			break;
		case 4:
			automobileInsurance.setMonthlyPremium(Integer.parseInt(input));
			productList.update(automobileInsurance);
			break;
		case 5:
			int contractPeriod = Integer.parseInt(input);
			automobileInsurance.setContractPeriod(contractPeriod);
			productList.update(automobileInsurance);
			break;
		case 6:
			automobileInsurance.setAccidentLimit(Integer.parseInt(input));
			productList.update(automobileInsurance);
			break;
		case 7:
			if (VehicleType.Small.ordinal() == (Integer.parseInt(input) - 1)) {
				automobileInsurance.setVehicleType(VehicleType.Small);
			} else if (VehicleType.Medium.ordinal() == (Integer.parseInt(input) - 1)) {
				automobileInsurance.setVehicleType(VehicleType.Medium);
			} else if (VehicleType.Large.ordinal() == (Integer.parseInt(input) - 1)) {
				automobileInsurance.setVehicleType(VehicleType.Large);
			}
			productList.update(automobileInsurance);
			break;
		case 8:
			automobileInsurance.setServiceList((ArrayList<ServiceType>) serviceTypeList.stream().distinct().collect(Collectors.toList()));
			productList.update(automobileInsurance);
			break;
		default:
			break;
		}
	}
	public ArrayList<Product> getAll(ProductList productList){
		return productList.getAll();
	}
}