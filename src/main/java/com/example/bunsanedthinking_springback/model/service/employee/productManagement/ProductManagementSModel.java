package com.example.bunsanedthinking_springback.model.service.employee.productManagement;

import com.example.bunsanedthinking_springback.dto.ProductManagement.AutomobileDTO;
import com.example.bunsanedthinking_springback.dto.ProductManagement.DiseaseDTO;
import com.example.bunsanedthinking_springback.dto.ProductManagement.InjuryDTO;
import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.domain.automobile.AutomobileDModel;
import com.example.bunsanedthinking_springback.model.domain.disease.DiseaseDModel;
import com.example.bunsanedthinking_springback.model.domain.injury.InjuryDModel;
import com.example.bunsanedthinking_springback.model.domain.insurance.InsuranceDModel;
import com.example.bunsanedthinking_springback.model.domain.product.ProductDModel;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-5-2024 占쏙옙占쏙옙 7:52:27
 */

@Service
public class ProductManagementSModel {

	@Autowired
	private ProductDModel productDModel;
	@Autowired
	private InsuranceDModel insuranceDModel;
	@Autowired
	private DiseaseDModel diseaseDModel;
	@Autowired
	private InjuryDModel injuryDModel;
	@Autowired
	private AutomobileDModel automobileDModel;

	public void addDiseaseInsurance(DiseaseDTO diseaseDTO) throws DuplicateInsuranceException {

		for (Product product : getAll()) {
			if (product.getName().equals(diseaseDTO.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		Integer insuranceId = insuranceDModel.getMaxId();
		if (insuranceId == null) {
			insuranceId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int insuranceSerialLength = ("" + Insurance.INSURANCE_SERIAL_NUMBER).length();
			String index = (insuranceId + "").substring(productSerialLength + insuranceSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + index;
			insuranceId = Integer.parseInt(compound);
		}

		Disease disease = new Disease();
		disease.setId(insuranceId);
		disease.setName(diseaseDTO.getName());
		disease.setMaximumMoney(diseaseDTO.getMaximumMoney());
		disease.setInsuranceType(InsuranceType.fromInt(diseaseDTO.getInsuranceType()));
		disease.setMonthlyPremium(diseaseDTO.getMonthlyPremium());
		disease.setContractPeriod(diseaseDTO.getContractPeriod());
		disease.setCoverage(diseaseDTO.getCoverage());

		disease.setDiseaseName(disease.getDiseaseName());
		disease.setDiseaseLimit(diseaseDTO.getDiseaseLimit());
		disease.setSurgeriesLimit(diseaseDTO.getSurgeriesLimit());

		diseaseDModel.add(disease);
	}

	public void addInjuryInsurance(InjuryDTO injuryDTO) throws DuplicateInsuranceException {
		for (Product product : getAll()) {
			if (product.getName().equals(injuryDTO.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		Integer insuranceId = insuranceDModel.getMaxId();
		if (insuranceId == null) {
			insuranceId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int insuranceSerialLength = ("" + Insurance.INSURANCE_SERIAL_NUMBER).length();
			String index = (insuranceId + "").substring(productSerialLength + insuranceSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + index;
			insuranceId = Integer.parseInt(compound);
		}

		Injury injury = new Injury();
		injury.setId(insuranceId);
		injury.setName(injuryDTO.getName());
		injury.setMaximumMoney(injuryDTO.getMaximumMoney());
		injury.setInsuranceType(InsuranceType.fromInt(injuryDTO.getInsuranceType()));
		injury.setMonthlyPremium(injuryDTO.getMonthlyPremium());
		injury.setContractPeriod(injuryDTO.getContractPeriod());
		injury.setCoverage(injuryDTO.getCoverage());

		injury.setInjuryType(InjuryType.fromInt(injuryDTO.getInsuranceType()));
		injury.setSurgeriesLimit(injuryDTO.getSurgeriesLimit());

		injuryDModel.add(injury);
	}

	public void addAutomobileInsurance(AutomobileDTO automobileDTO) throws DuplicateInsuranceException {
		for (Product product : getAll()) {
			if (product.getName().equals(automobileDTO.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		Integer insuranceId = injuryDModel.getMaxId();
		if (insuranceId == null) {
			insuranceId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int insuranceSerialLength = ("" + Insurance.INSURANCE_SERIAL_NUMBER).length();
			String index = (insuranceId + "").substring(productSerialLength + insuranceSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + index;
			insuranceId = Integer.parseInt(compound);
		}


		Automobile automobile = new Automobile();
		automobile.setId(insuranceId);
		automobile.setName(automobileDTO.getName());
		automobile.setMaximumMoney(automobileDTO.getMaximumMoney());
		automobile.setInsuranceType(InsuranceType.fromInt(automobileDTO.getInsuranceType()));
		automobile.setMonthlyPremium(automobileDTO.getMonthlyPremium());
		automobile.setContractPeriod(automobileDTO.getContractPeriod());
		automobile.setCoverage(automobileDTO.getCoverage());

		automobile.setVehicleType(VehicleType.fromInt(automobileDTO.getVehicleType()));
		automobile.setAccidentLimit(automobileDTO.getAccidentLimit());
		automobile.setServiceList((ArrayList<ServiceType>)automobileDTO.getServiceList());

		automobileDModel.add(automobile);
	}

	public void deleteInsuranceProduct(int id) {
		productDModel.delete(id);
	}

	public Insurance getInsuranceProduct(int id) throws NotExistException {
		try {
			Insurance insurance = insuranceDModel.getById(id);
			if (insurance == null) {
				throw new NotExistException("해당하는 보험 상품 정보가 존재하지 않습니다.");
			}
			return insurance;
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 보험 상품 정보가 존재하지 않습니다.");
		}
	}

	public void updateDiseaseInsurance(int index, String input, int id) throws DuplicateInsuranceException {

		Disease disease = diseaseDModel.getById(id);

		switch (index) {
			case 1:
				for (Product product : getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				disease.setName(input);
				diseaseDModel.update(disease);
				break;
			case 2:
				disease.setAgeRange(Integer.parseInt(input));
				diseaseDModel.update(disease);
				break;
			case 3:
				disease.setCoverage(input);
				diseaseDModel.update(disease);
				break;
			case 4:
				disease.setMonthlyPremium(Integer.parseInt(input));
				diseaseDModel.update(disease);
				break;
			case 5:
				disease.setContractPeriod(Integer.parseInt(input));
				diseaseDModel.update(disease);
				break;
			case 6:
				disease.setDiseaseLimit(Integer.parseInt(input));
				diseaseDModel.update(disease);
				break;
			case 7:
				disease.setDiseaseName(input);
				diseaseDModel.update(disease);
				break;
			case 8:
				disease.setSurgeriesLimit(Integer.parseInt(input));
				diseaseDModel.update(disease);
				break;
			default:
				break;
		}
	}

	public void updateInjuryInsurance(int index, String input, int id) throws DuplicateInsuranceException {
		Injury injury = injuryDModel.getById(id);

		switch (index) {
			case 1:
				for (Product product : getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				injury.setName(input);
				injuryDModel.update(injury);
				break;
			case 2:
				injury.setAgeRange(Integer.parseInt(input));
				injuryDModel.update(injury);
				break;
			case 3:
				injury.setCoverage(input);
				injuryDModel.update(injury);
				break;
			case 4:
				injury.setMonthlyPremium(Integer.parseInt(input));
				injuryDModel.update(injury);
				break;
			case 5:
				injury.setContractPeriod(Integer.parseInt(input));
				injuryDModel.update(injury);
				break;
			case 6:
				if (InjuryType.Minor.ordinal() == (Integer.parseInt(input) - 1)) {
					injury.setInjuryType(InjuryType.Minor);
				} else if (InjuryType.Serious.ordinal() == (Integer.parseInt(input) - 1)) {
					injury.setInjuryType(InjuryType.Serious);
				}
				injuryDModel.update(injury);
				break;
			case 7:
				injury.setSurgeriesLimit(Integer.parseInt(input));
				injuryDModel.update(injury);
				break;
			default:
				break;
		}
	}

	public void updateAutomobileInsurance(int index, String input, int id,
		ArrayList<ServiceType> serviceTypeList) throws DuplicateInsuranceException {

		Automobile automobile = automobileDModel.getById(id);

		switch (index) {
			case 1:
				for (Product product : getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				automobile.setName(input);
				automobileDModel.update(automobile);
				break;
			case 2:
				automobile.setAgeRange(Integer.parseInt(input));
				automobileDModel.update(automobile);
				break;
			case 3:
				automobile.setCoverage(input);
				automobileDModel.update(automobile);
				break;
			case 4:
				automobile.setMonthlyPremium(Integer.parseInt(input));
				automobileDModel.update(automobile);
				break;
			case 5:
				automobile.setContractPeriod(Integer.parseInt(input));
				automobileDModel.update(automobile);
				break;
			case 6:
				automobile.setAccidentLimit(Integer.parseInt(input));
				automobileDModel.update(automobile);
				break;
			case 7:
				if (VehicleType.Small.ordinal() == (Integer.parseInt(input) - 1)) {
					automobile.setVehicleType(VehicleType.Small);
				} else if (VehicleType.Medium.ordinal() == (Integer.parseInt(input) - 1)) {
					automobile.setVehicleType(VehicleType.Medium);
				} else if (VehicleType.Large.ordinal() == (Integer.parseInt(input) - 1)) {
					automobile.setVehicleType(VehicleType.Large);
				}
				automobileDModel.update(automobile);
				break;
			case 8:
				ArrayList<ServiceType> distinctServiceList = (ArrayList<ServiceType>)serviceTypeList.stream()
					.distinct()
					.collect(Collectors.toList());
				automobile.setServiceList(distinctServiceList);
				automobileDModel.update(automobile);
				break;
			default:
				break;
		}

	}

	public ArrayList<Product> getAll() {
		return (ArrayList<Product>)productDModel.getAll();
	}
}
