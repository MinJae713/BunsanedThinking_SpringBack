package com.example.bunsanedthinking_springback.model.service.employee.productManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddAutomobileInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddDiseaseInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.request.AddInjuryInsuranceRequest;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductAutomobileDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductDiseaseDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductDetailResponse.ManageInsuranceProductInjuryDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.productManagement.response.ManageInsuranceProductResponse;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.InjuryType;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.insurance.InsuranceType;
import com.example.bunsanedthinking_springback.entity.insurance.ServiceType;
import com.example.bunsanedthinking_springback.entity.insurance.VehicleType;
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

	public void addDiseaseInsurance(AddDiseaseInsuranceRequest addDiseaseInsuranceRequest) throws DuplicateInsuranceException {

		for (Product product : productEntityModel.getAll()) {
			if (product.getName().equals(addDiseaseInsuranceRequest.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		int insuranceId = NextIdGetter.getNextId(insuranceEntityModel.getMaxId(), Integer.parseInt("" + PRODUCT_SERIAL_NUMBER + INSURANCE_SERIAL_NUMBER));

		Disease disease = new Disease();
		disease.setId(insuranceId);
		disease.setName(addDiseaseInsuranceRequest.getName());
		disease.setMaximumMoney(addDiseaseInsuranceRequest.getMaximumMoney());
		disease.setInsuranceType(InsuranceType.fromInt(addDiseaseInsuranceRequest.getInsuranceType()));
		disease.setMonthlyPremium(addDiseaseInsuranceRequest.getMonthlyPremium());
		disease.setContractPeriod(addDiseaseInsuranceRequest.getContractPeriod());
		disease.setCoverage(addDiseaseInsuranceRequest.getCoverage());

		disease.setDiseaseName(disease.getDiseaseName());
		disease.setDiseaseLimit(addDiseaseInsuranceRequest.getDiseaseLimit());
		disease.setSurgeriesLimit(addDiseaseInsuranceRequest.getSurgeriesLimit());

		diseaseEntityModel.add(disease);
	}

	public void addInjuryInsurance(AddInjuryInsuranceRequest addInjuryInsuranceRequest) throws DuplicateInsuranceException {
		for (Product product : productEntityModel.getAll()) {
			if (product.getName().equals(addInjuryInsuranceRequest.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		int insuranceId = NextIdGetter.getNextId(insuranceEntityModel.getMaxId(), Integer.parseInt("" + PRODUCT_SERIAL_NUMBER + INSURANCE_SERIAL_NUMBER));

		Injury injury = new Injury();
		injury.setId(insuranceId);
		injury.setName(addInjuryInsuranceRequest.getName());
		injury.setMaximumMoney(addInjuryInsuranceRequest.getMaximumMoney());
		injury.setInsuranceType(InsuranceType.fromInt(addInjuryInsuranceRequest.getInsuranceType()));
		injury.setMonthlyPremium(addInjuryInsuranceRequest.getMonthlyPremium());
		injury.setContractPeriod(addInjuryInsuranceRequest.getContractPeriod());
		injury.setCoverage(addInjuryInsuranceRequest.getCoverage());

		injury.setInjuryType(InjuryType.fromInt(addInjuryInsuranceRequest.getInjuryType()));
		injury.setSurgeriesLimit(addInjuryInsuranceRequest.getSurgeriesLimit());

		injuryEntityModel.add(injury);
	}

	public void addAutomobileInsurance(AddAutomobileInsuranceRequest addAutomobileInsuranceRequest) throws DuplicateInsuranceException {
		for (Product product : productEntityModel.getAll()) {
			if (product.getName().equals(addAutomobileInsuranceRequest.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		int insuranceId = NextIdGetter.getNextId(insuranceEntityModel.getMaxId(), Integer.parseInt("" + PRODUCT_SERIAL_NUMBER + INSURANCE_SERIAL_NUMBER));

		Automobile automobile = new Automobile();
		automobile.setId(insuranceId);
		automobile.setName(addAutomobileInsuranceRequest.getName());
		automobile.setMaximumMoney(addAutomobileInsuranceRequest.getMaximumMoney());
		automobile.setInsuranceType(InsuranceType.fromInt(addAutomobileInsuranceRequest.getInsuranceType()));
		automobile.setMonthlyPremium(addAutomobileInsuranceRequest.getMonthlyPremium());
		automobile.setContractPeriod(addAutomobileInsuranceRequest.getContractPeriod());
		automobile.setCoverage(addAutomobileInsuranceRequest.getCoverage());

		automobile.setVehicleType(VehicleType.fromInt(addAutomobileInsuranceRequest.getVehicleType()));
		automobile.setAccidentLimit(addAutomobileInsuranceRequest.getAccidentLimit());
		automobile.setServiceList((ArrayList<ServiceType>) addAutomobileInsuranceRequest.getServiceList());

		automobileEntityModel.add(automobile);
	}

	public void deleteInsuranceProduct(int id) {
		productEntityModel.delete(id);
	}

	public Insurance getInsuranceProduct(int id) throws NotExistException {
		try {
			Insurance insurance = insuranceEntityModel.getById(id);
			if (insurance == null) {
				throw new NotExistException("해당하는 보험 상품 정보가 존재하지 않습니다.");
			}
			return insurance;
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 보험 상품 정보가 존재하지 않습니다.");
		}
	}

	public void updateDiseaseInsurance(int index, String input, int id) throws DuplicateInsuranceException {

		Disease disease = diseaseEntityModel.getById(id);

		switch (index) {
			case 1:
				for (Product product : productEntityModel.getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				disease.setName(input);
				diseaseEntityModel.update(disease);
				break;
			case 2:
				disease.setAgeRange(Integer.parseInt(input));
				diseaseEntityModel.update(disease);
				break;
			case 3:
				disease.setCoverage(input);
				diseaseEntityModel.update(disease);
				break;
			case 4:
				disease.setMonthlyPremium(Integer.parseInt(input));
				diseaseEntityModel.update(disease);
				break;
			case 5:
				disease.setContractPeriod(Integer.parseInt(input));
				diseaseEntityModel.update(disease);
				break;
			case 6:
				disease.setDiseaseLimit(Integer.parseInt(input));
				diseaseEntityModel.update(disease);
				break;
			case 7:
				disease.setDiseaseName(input);
				diseaseEntityModel.update(disease);
				break;
			case 8:
				disease.setSurgeriesLimit(Integer.parseInt(input));
				diseaseEntityModel.update(disease);
				break;
			default:
				break;
		}
	}

	public void updateInjuryInsurance(int index, String input, int id) throws DuplicateInsuranceException {
		Injury injury = injuryEntityModel.getById(id);

		switch (index) {
			case 1:
				for (Product product : productEntityModel.getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				injury.setName(input);
				injuryEntityModel.update(injury);
				break;
			case 2:
				injury.setAgeRange(Integer.parseInt(input));
				injuryEntityModel.update(injury);
				break;
			case 3:
				injury.setCoverage(input);
				injuryEntityModel.update(injury);
				break;
			case 4:
				injury.setMonthlyPremium(Integer.parseInt(input));
				injuryEntityModel.update(injury);
				break;
			case 5:
				injury.setContractPeriod(Integer.parseInt(input));
				injuryEntityModel.update(injury);
				break;
			case 6:
				if (InjuryType.Minor.ordinal() == (Integer.parseInt(input) - 1)) {
					injury.setInjuryType(InjuryType.Minor);
				} else if (InjuryType.Serious.ordinal() == (Integer.parseInt(input) - 1)) {
					injury.setInjuryType(InjuryType.Serious);
				}
				injuryEntityModel.update(injury);
				break;
			case 7:
				injury.setSurgeriesLimit(Integer.parseInt(input));
				injuryEntityModel.update(injury);
				break;
			default:
				break;
		}
	}

	public void updateAutomobileInsurance(int index, String input, int id,
		ArrayList<ServiceType> serviceTypeList) throws DuplicateInsuranceException {

		Automobile automobile = automobileEntityModel.getById(id);

		switch (index) {
			case 1:
				for (Product product : productEntityModel.getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				automobile.setName(input);
				automobileEntityModel.update(automobile);
				break;
			case 2:
				automobile.setAgeRange(Integer.parseInt(input));
				automobileEntityModel.update(automobile);
				break;
			case 3:
				automobile.setCoverage(input);
				automobileEntityModel.update(automobile);
				break;
			case 4:
				automobile.setMonthlyPremium(Integer.parseInt(input));
				automobileEntityModel.update(automobile);
				break;
			case 5:
				automobile.setContractPeriod(Integer.parseInt(input));
				automobileEntityModel.update(automobile);
				break;
			case 6:
				automobile.setAccidentLimit(Integer.parseInt(input));
				automobileEntityModel.update(automobile);
				break;
			case 7:
				if (VehicleType.Small.ordinal() == (Integer.parseInt(input) - 1)) {
					automobile.setVehicleType(VehicleType.Small);
				} else if (VehicleType.Medium.ordinal() == (Integer.parseInt(input) - 1)) {
					automobile.setVehicleType(VehicleType.Medium);
				} else if (VehicleType.Large.ordinal() == (Integer.parseInt(input) - 1)) {
					automobile.setVehicleType(VehicleType.Large);
				}
				automobileEntityModel.update(automobile);
				break;
			case 8:
				ArrayList<ServiceType> distinctServiceList = (ArrayList<ServiceType>)serviceTypeList.stream()
					.distinct()
					.collect(Collectors.toList());
				automobile.setServiceList(distinctServiceList);
				automobileEntityModel.update(automobile);
				break;
			default:
				break;
		}

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
		if(insurance instanceof Injury)
			return ManageInsuranceProductInjuryDetailResponse.from((Injury)insurance);
		else if (insurance instanceof Disease)
			return ManageInsuranceProductDiseaseDetailResponse.from((Disease)insurance);
		else if (insurance instanceof Automobile)
			return ManageInsuranceProductAutomobileDetailResponse.from((Automobile)insurance);
		throw new NotExistException();
	}
}
