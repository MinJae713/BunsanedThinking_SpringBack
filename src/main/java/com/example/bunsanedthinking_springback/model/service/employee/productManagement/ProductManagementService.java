package com.example.bunsanedthinking_springback.model.service.employee.productManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bunsanedthinking_springback.global.constants.common.CommonConstants;
import com.example.bunsanedthinking_springback.global.constants.serial.Serial;
import com.example.bunsanedthinking_springback.global.constants.service.customer.service.ProductManagementConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddAutomobileInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddDiseaseInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddInjuryInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.UpdateAutomobileInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.UpdateDiseaseInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.UpdateInjuryInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductAutomobileDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductDiseaseDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductInjuryDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductResponse;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.global.util.NextIdGetter;
import com.example.bunsanedthinking_springback.model.entityModel.automobile.AutomobileEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.disease.DiseaseEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.injury.InjuryEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insurance.InsuranceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.product.ProductEntityModel;

/**
 * @author Administrator
 * @version 1.0
 * @created 20-5-2024 占쏙옙占쏙옙 7:52:27
 */

@Service
public class ProductManagementService {

	@Autowired
	private Serial serial;

	@Autowired
	private ProductEntityModel productEntityModel;
	@Autowired
	private InsuranceEntityModel insuranceEntityModel;
	@Autowired
	private DiseaseEntityModel diseaseEntityModel;
	@Autowired
	private InjuryEntityModel injuryEntityModel;
	@Autowired
	private AutomobileEntityModel automobileEntityModel;

	@Value("${serials.product}")
	private Integer PRODUCT_SERIAL_NUMBER;

	@Value("${serials.insurance}")
	private Integer INSURANCE_SERIAL_NUMBER;

	public void addDiseaseInsurance(AddDiseaseInsuranceRequest addDiseaseInsuranceRequest) throws
		DuplicateInsuranceException {

		for (Product product : productEntityModel.getAll()) {
			if (product.getName().equals(addDiseaseInsuranceRequest.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		int insuranceId = NextIdGetter.getNextId(insuranceEntityModel.getMaxId(),
			Integer.parseInt(CommonConstants.STRING_EMPTY + serial.getProduct() + serial.getInsurance()));

		Disease disease = new Disease();
		disease.setId(insuranceId);
		disease.setName(addDiseaseInsuranceRequest.getName());
		disease.setMaximumMoney(addDiseaseInsuranceRequest.getMaximumMoney());
		disease.setInsuranceType(addDiseaseInsuranceRequest.getInsuranceType());
		disease.setMonthlyPremium(addDiseaseInsuranceRequest.getMonthlyPremium());
		disease.setContractPeriod(addDiseaseInsuranceRequest.getContractPeriod());
		disease.setCoverage(addDiseaseInsuranceRequest.getCoverage());
		disease.setAgeRange(addDiseaseInsuranceRequest.getAgeRange());

		disease.setDiseaseName(addDiseaseInsuranceRequest.getDiseaseName());
		disease.setDiseaseLimit(addDiseaseInsuranceRequest.getDiseaseLimit());
		disease.setSurgeriesLimit(addDiseaseInsuranceRequest.getSurgeriesLimit());

		diseaseEntityModel.add(disease);
	}

	public void addInjuryInsurance(AddInjuryInsuranceRequest addInjuryInsuranceRequest) throws
		DuplicateInsuranceException {
		for (Product product : productEntityModel.getAll()) {
			if (product.getName().equals(addInjuryInsuranceRequest.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		int insuranceId = NextIdGetter.getNextId(insuranceEntityModel.getMaxId(),
			Integer.parseInt(CommonConstants.STRING_EMPTY + serial.getProduct() + serial.getInsurance()));

		Injury injury = new Injury();
		injury.setId(insuranceId);
		injury.setName(addInjuryInsuranceRequest.getName());
		injury.setMaximumMoney(addInjuryInsuranceRequest.getMaximumMoney());
		injury.setInsuranceType(addInjuryInsuranceRequest.getInsuranceType());
		injury.setMonthlyPremium(addInjuryInsuranceRequest.getMonthlyPremium());
		injury.setContractPeriod(addInjuryInsuranceRequest.getContractPeriod());
		injury.setCoverage(addInjuryInsuranceRequest.getCoverage());
		injury.setAgeRange(addInjuryInsuranceRequest.getAgeRange());

		injury.setInjuryType(addInjuryInsuranceRequest.getInjuryType());
		injury.setSurgeriesLimit(addInjuryInsuranceRequest.getSurgeriesLimit());

		injuryEntityModel.add(injury);
	}

	public void addAutomobileInsurance(AddAutomobileInsuranceRequest addAutomobileInsuranceRequest) throws
		DuplicateInsuranceException {
		for (Product product : productEntityModel.getAll()) {
			if (product.getName().equals(addAutomobileInsuranceRequest.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		int insuranceId = NextIdGetter.getNextId(insuranceEntityModel.getMaxId(),
			Integer.parseInt(CommonConstants.STRING_EMPTY + serial.getProduct() + serial.getInsurance()));

		Automobile automobile = new Automobile();
		automobile.setId(insuranceId);
		automobile.setName(addAutomobileInsuranceRequest.getName());
		automobile.setMaximumMoney(addAutomobileInsuranceRequest.getMaximumMoney());
		automobile.setInsuranceType(addAutomobileInsuranceRequest.getInsuranceType());
		automobile.setMonthlyPremium(addAutomobileInsuranceRequest.getMonthlyPremium());
		automobile.setContractPeriod(addAutomobileInsuranceRequest.getContractPeriod());
		automobile.setCoverage(addAutomobileInsuranceRequest.getCoverage());
		automobile.setAgeRange(addAutomobileInsuranceRequest.getAgeRange());

		automobile.setVehicleType(addAutomobileInsuranceRequest.getVehicleType());
		automobile.setAccidentLimit(addAutomobileInsuranceRequest.getAccidentLimit());
		automobile.setServiceList((ArrayList<ServiceType>)addAutomobileInsuranceRequest.getServiceTypes());

		automobileEntityModel.add(automobile);
	}

	public void deleteInsuranceProduct(int id) {
		productEntityModel.delete(id);
	}

	public ManageInsuranceProductResponse getInsuranceProduct(int id) throws NotExistException {
		try {
			Insurance insurance = insuranceEntityModel.getById(id);
			if (insurance == null) {
				throw new NotExistException(ProductManagementConstants.INSURANCE_PRODUCT_NOT_FOUND);
			}
			return ManageInsuranceProductResponse.from(insurance);
		} catch (NotExistException e) {
			throw new NotExistException(ProductManagementConstants.INSURANCE_PRODUCT_NOT_FOUND);
		}
	}

	public void updateDiseaseInsurance(UpdateDiseaseInsuranceRequest updateDiseaseInsuranceRequest) throws
		DuplicateInsuranceException {

		Disease origin = diseaseEntityModel.getById(updateDiseaseInsuranceRequest.getId());
		if (!updateDiseaseInsuranceRequest.getName().equals(origin.getName())) {
			for (Product product : productEntityModel.getAll()) {
				if (product.getName().equals(updateDiseaseInsuranceRequest.getName())) {
					throw new DuplicateInsuranceException();
				}
			}
		}
		diseaseEntityModel.update(updateDiseaseInsuranceRequest.toEntity());
	}

	public void updateInjuryInsurance(UpdateInjuryInsuranceRequest updateInjuryInsuranceRequest) throws
		DuplicateInsuranceException {

		Injury origin = injuryEntityModel.getById(updateInjuryInsuranceRequest.getId());
		if (!updateInjuryInsuranceRequest.getName().equals(origin.getName())) {
			for (Product product : productEntityModel.getAll()) {
				if (product.getName().equals(updateInjuryInsuranceRequest.getName())) {
					throw new DuplicateInsuranceException();
				}
			}
		}
		injuryEntityModel.update(updateInjuryInsuranceRequest.toEntity());
	}

	public void updateAutomobileInsurance(UpdateAutomobileInsuranceRequest updateAutomobileInsuranceRequest) throws
		DuplicateInsuranceException {
		Automobile origin = automobileEntityModel.getById(updateAutomobileInsuranceRequest.getId());
		if (!updateAutomobileInsuranceRequest.getName().equals(origin.getName())) {
			for (Product product : productEntityModel.getAll()) {
				if (product.getName().equals(updateAutomobileInsuranceRequest.getName())) {
					throw new DuplicateInsuranceException();
				}
			}
		}
		automobileEntityModel.update(updateAutomobileInsuranceRequest.toEntity());
	}

	public List<ManageInsuranceProductResponse> getAllInsurance() {
		List<Insurance> insurances = insuranceEntityModel.getAll();
		return insurances.stream().map(ManageInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public List<ManageInsuranceProductResponse> getAllinJuryInsurance() {
		List<Injury> injuries = injuryEntityModel.getAll();
		return injuries.stream().map(ManageInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public List<ManageInsuranceProductResponse> getAllAutomobileInsurance() {
		List<Automobile> automobiles = automobileEntityModel.getAll();
		return automobiles.stream().map(ManageInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public List<ManageInsuranceProductResponse> getAllDiseaseInsurance() {
		List<Disease> diseases = diseaseEntityModel.getAll();
		return diseases.stream().map(ManageInsuranceProductResponse::from).collect(Collectors.toList());
	}

	public ManageInsuranceProductDetailResponse getInsuranceProductDetail(int id) throws NotExistException {
		Insurance insurance = insuranceEntityModel.getById(id);
		if (insurance instanceof Injury)
			return ManageInsuranceProductInjuryDetailResponse.from((Injury)insurance);
		else if (insurance instanceof Disease)
			return ManageInsuranceProductDiseaseDetailResponse.from((Disease)insurance);
		else if (insurance instanceof Automobile)
			return ManageInsuranceProductAutomobileDetailResponse.from((Automobile)insurance);
		throw new NotExistException();
	}
}
