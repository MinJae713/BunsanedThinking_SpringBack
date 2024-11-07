package com.example.bunsanedthinking_springback.model.productManagement;

import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.global.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
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
public class ProductManagementModel {

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;
	@Autowired
	private AutomobileMapper automobileMapper;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private InjuryMapper injuryMapper;

	public void addDiseaseInsurance(Disease disease) throws DuplicateInsuranceException {

		for (Product product : getAll()) {
			if (product.getName().equals(disease.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		Integer productId = insuranceMapper.getMaxId_ProductManagementModel();
		if (productId == null) {
			productId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int insuranceSerialLength = ("" + Insurance.INSURANCE_SERIAL_NUMBER).length();
			String index = (productId + "").substring(productSerialLength + insuranceSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + index;
			productId = Integer.parseInt(compound);
		}
		disease.setId(productId);

		ProductVO productVO = new ProductVO();
		productVO.setId(productId);
		productVO.setName(disease.getName());
		productVO.setMaximum_money(disease.getMaximumMoney());
		productMapper.insert_LoanManagement(productVO);

		InsuranceVO insuranceVO = new InsuranceVO();
		insuranceVO.setProduct_id(productId);
		insuranceVO.setInsurance_type(disease.getInsuranceType().getValue());
		insuranceVO.setMonthly_premium(disease.getMonthlyPremium());
		insuranceVO.setContract_period(disease.getContractPeriod());
		insuranceVO.setCoverage(disease.getCoverage());
		insuranceMapper.insert_ProductManagement(insuranceVO);

		DiseaseVO diseaseVO = new DiseaseVO();
		diseaseVO.setProduct_id(productId);
		diseaseVO.setDisease_name(disease.getName());
		diseaseVO.setDisease_limit(disease.getDiseaseLimit());
		diseaseVO.setSurgeries_limit(disease.getSurgeriesLimit());
		diseaseMapper.insert_ProductManagement(diseaseVO);
		// productList.add(diseaseInsurance);
	}

	public void addInjuryInsurance(Injury injury) throws DuplicateInsuranceException {
		for (Product product : getAll()) {
			if (product.getName().equals(injury.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		Integer productId = insuranceMapper.getMaxId_ProductManagementModel();
		if (productId == null) {
			productId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int insuranceSerialLength = ("" + Insurance.INSURANCE_SERIAL_NUMBER).length();
			String index = (productId + "").substring(productSerialLength + insuranceSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + index;
			productId = Integer.parseInt(compound);
		}
		injury.setId(productId);

		ProductVO productVO = new ProductVO();
		productVO.setId(productId);
		productVO.setName(injury.getName());
		productVO.setMaximum_money(injury.getMaximumMoney());
		productMapper.insert_LoanManagement(productVO);

		InsuranceVO insuranceVO = new InsuranceVO();
		insuranceVO.setProduct_id(productId);
		insuranceVO.setInsurance_type(injury.getInsuranceType().getValue());
		insuranceVO.setMonthly_premium(injury.getMonthlyPremium());
		insuranceVO.setContract_period(injury.getContractPeriod());
		insuranceVO.setCoverage(injury.getCoverage());
		insuranceMapper.insert_ProductManagement(insuranceVO);

		InjuryVO injuryVO = new InjuryVO();
		injuryVO.setProduct_id(productId);
		injuryVO.setInjury_type(injury.getInjuryType().getValue());
		injuryVO.setSurgeries_limit(injury.getSurgeriesLimit());
		injuryMapper.insert_ProductManagement(injuryVO);

		// productList.add(injury);
	}

	public void addAutomobileInsurance(Automobile automobile) throws DuplicateInsuranceException {
		for (Product product : getAll()) {
			if (product.getName().equals(automobile.getName())) {
				throw new DuplicateInsuranceException();
			}
		}

		Integer productId = insuranceMapper.getMaxId_ProductManagementModel();
		if (productId == null) {
			productId = Integer.parseInt("" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + 1);
		} else {
			int productSerialLength = ("" + Product.PRODUCT_SERIAL_NUMBER).length();
			int insuranceSerialLength = ("" + Insurance.INSURANCE_SERIAL_NUMBER).length();
			String index = (productId + "").substring(productSerialLength + insuranceSerialLength);
			index = (Integer.parseInt(index) + 1) + "";
			String compound = "" + Product.PRODUCT_SERIAL_NUMBER + Insurance.INSURANCE_SERIAL_NUMBER + index;
			productId = Integer.parseInt(compound);
		}
		automobile.setId(productId);

		ProductVO productVO = new ProductVO();
		productVO.setId(automobile.getId());
		productVO.setName(automobile.getName());
		productVO.setMaximum_money(automobile.getMaximumMoney());
		productMapper.insert_LoanManagement(productVO);

		InsuranceVO insuranceVO = new InsuranceVO();
		insuranceVO.setProduct_id(automobile.getId());
		insuranceVO.setInsurance_type(automobile.getInsuranceType().getValue());
		insuranceVO.setMonthly_premium(automobile.getMonthlyPremium());
		insuranceVO.setContract_period(automobile.getContractPeriod());
		insuranceVO.setCoverage(automobile.getCoverage());
		insuranceMapper.insert_ProductManagement(insuranceVO);

		AutoMobileVO automobileVO = new AutoMobileVO();
		automobileVO.setProduct_id(automobile.getId());
		automobileVO.setVehicle_type(automobile.getVehicleType().getValue());
		automobileVO.setAccident_limit(automobile.getAccidentLimit());
		automobileMapper.insert_ProductManagement(automobileVO);

		for (ServiceType serviceType : automobile.getServiceList()) {
			ServiceVO serviceVO = new ServiceVO();
			serviceVO.setProduct_id(automobile.getId());
			serviceVO.setService(serviceType.getValue());
			serviceMapper.insert_ProductManagement(serviceVO);
		}

		// productList.add(automobile);
	}

	public void deleteInsuranceProduct(int id) {
		productMapper.delete_ProductManagementModel(id);
		// productList.delete(id);
	}

	public Insurance getInsuranceProduct(int id) throws NotExistException {
		try {
			Insurance insurance = null;

			ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
			for (DiseaseVO diseaseVO : diseaseVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
				if (productVO.getId() == id) {
					Disease disease = new Disease();

					disease.setId(insuranceVO.getProduct_id());
					disease.setName(productVO.getName());
					disease.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
					disease.setAgeRange(insuranceVO.getAge_range());
					disease.setCoverage(insuranceVO.getCoverage());
					disease.setMonthlyPremium(insuranceVO.getMonthly_premium());
					disease.setContractPeriod(insuranceVO.getContract_period());

					disease.setDiseaseLimit(diseaseVO.getDisease_limit());
					disease.setDiseaseName(diseaseVO.getDisease_name());
					disease.setSurgeriesLimit(diseaseVO.getSurgeries_limit());

					insurance = disease;
				}
			}
			ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
			for (InjuryVO injuryVO : injuryVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
				if (productVO.getId() == id) {
					Injury injury = new Injury();

					injury.setId(insuranceVO.getProduct_id());
					injury.setName(productVO.getName());
					injury.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
					injury.setAgeRange(insuranceVO.getAge_range());
					injury.setCoverage(insuranceVO.getCoverage());
					injury.setMonthlyPremium(insuranceVO.getMonthly_premium());
					injury.setContractPeriod(insuranceVO.getContract_period());

					injury.setInjuryType(InjuryType.fromInt(injuryVO.getInjury_type()));
					injury.setSurgeriesLimit(injuryVO.getSurgeries_limit());

					insurance = injury;
				}
			}
			ArrayList<AutoMobileVO> AutoMobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
			for (AutoMobileVO automobileVO : AutoMobileVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());

				if (productVO.getId() == id) {
					Automobile automobile = new Automobile();

					automobile.setId(insuranceVO.getProduct_id());
					automobile.setName(productVO.getName());
					automobile.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
					automobile.setAgeRange(insuranceVO.getAge_range());
					automobile.setCoverage(insuranceVO.getCoverage());
					automobile.setMonthlyPremium(insuranceVO.getMonthly_premium());
					automobile.setContractPeriod(insuranceVO.getContract_period());

					automobile.setAccidentLimit(automobileVO.getAccident_limit());
					automobile.setVehicleType(VehicleType.fromInt(automobileVO.getVehicle_type()));
					ArrayList<ServiceType> serviceTypes = new ArrayList<>();
					ArrayList<ServiceVO> serviceVOs = serviceMapper.get_SalesModel(id);
					for (ServiceVO serviceVO : serviceVOs)
						serviceTypes.add(ServiceType.fromInt(serviceVO.getService()));
					automobile.setServiceList(serviceTypes);

					insurance = automobile;
				}
			}

			if (insurance == null) {
				throw new NotExistException("해당하는 보험 상품 정보가 존재하지 않습니다.");
			}
			return insurance;
			// return (Insurance) productList.get(id);
		} catch (NotExistException e) {
			throw new NotExistException("해당하는 보험 상품 정보가 존재하지 않습니다.");
		}
	}

	public void updateDiseaseInsurance(int index, String input, int id) throws DuplicateInsuranceException {

		DiseaseVO diseaseVO = diseaseMapper.getById_ProductManagementModel(id);
		ProductVO productVO = productMapper.getById_ProductManagementModel(diseaseVO.getProduct_id());
		InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(diseaseVO.getProduct_id());

		switch (index) {
			case 1:
				for (Product product : getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				productVO.setName(input);
				productMapper.updateName_ProductManagementModel(productVO);
				// productList.update(diseaseInsurance);
				break;
			case 2:
				insuranceVO.setAge_range(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				break;
			case 3:
				insuranceVO.setCoverage(input);
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 4:
				insuranceVO.setMonthly_premium(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 5:
				insuranceVO.setContract_period(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 6:
				diseaseVO.setDisease_limit(Integer.parseInt(input));
				diseaseMapper.update_ProductManagementModel(diseaseVO);
				// productList.update(diseaseInsurance);
				break;
			case 7:
				diseaseVO.setDisease_name(input);
				diseaseMapper.update_ProductManagementModel(diseaseVO);
				// productList.update(diseaseInsurance);
				break;
			case 8:
				diseaseVO.setSurgeries_limit(Integer.parseInt(input));
				diseaseMapper.update_ProductManagementModel(diseaseVO);
				// productList.update(diseaseInsurance);
				break;
			default:
				break;
		}
	}

	public void updateInjuryInsurance(int index, String input, int id) throws DuplicateInsuranceException {
		InjuryVO injuryVO = injuryMapper.getById_ProductManagementModel(id);
		ProductVO productVO = productMapper.getById_ProductManagementModel(injuryVO.getProduct_id());
		InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(injuryVO.getProduct_id());

		switch (index) {
			case 1:
				for (Product product : getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				productVO.setName(input);
				productMapper.updateName_ProductManagementModel(productVO);
				// productList.update(diseaseInsurance);
				break;
			case 2:
				insuranceVO.setAge_range(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				break;
			case 3:
				insuranceVO.setCoverage(input);
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 4:
				insuranceVO.setMonthly_premium(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 5:
				insuranceVO.setContract_period(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 6:
				if (InjuryType.Minor.ordinal() == (Integer.parseInt(input) - 1)) {
					injuryVO.setInjury_type(InjuryType.Minor.getValue());
				} else if (InjuryType.Serious.ordinal() == (Integer.parseInt(input) - 1)) {
					injuryVO.setInjury_type(InjuryType.Serious.getValue());
				}
				injuryMapper.update_ProductManagementModel(injuryVO);
				// productList.update(injuryInsurance);
				break;
			case 7:
				injuryVO.setSurgeries_limit(Integer.parseInt(input));
				injuryMapper.update_ProductManagementModel(injuryVO);
				// productList.update(injuryInsurance);
				break;
			default:
				break;
		}
	}

	public void updateAutomobileInsurance(int index, String input, int id,
		ArrayList<ServiceType> serviceTypeList) throws DuplicateInsuranceException {

		AutoMobileVO autoMobileVO = automobileMapper.getById_ProductManagementModel(id);
		ProductVO productVO = productMapper.getById_ProductManagementModel(autoMobileVO.getProduct_id());
		InsuranceVO insuranceVO = insuranceMapper.getById_ProductManagementModel(autoMobileVO.getProduct_id());

		switch (index) {
			case 1:
				for (Product product : getAll()) {
					if (product.getName().equals(input)) {
						throw new DuplicateInsuranceException();
					}
				}
				productVO.setName(input);
				productMapper.updateName_ProductManagementModel(productVO);
				// productList.update(diseaseInsurance);
				break;
			case 2:
				insuranceVO.setAge_range(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				break;
			case 3:
				insuranceVO.setCoverage(input);
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 4:
				insuranceVO.setMonthly_premium(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 5:
				insuranceVO.setContract_period(Integer.parseInt(input));
				insuranceMapper.update_ProductManagementModel(insuranceVO);
				// productList.update(diseaseInsurance);
				break;
			case 6:
				autoMobileVO.setAccident_limit(Integer.parseInt(input));
				automobileMapper.update_ProductManagementModel(autoMobileVO);
				// productList.update(automobileInsurance);
				break;
			case 7:
				if (VehicleType.Small.ordinal() == (Integer.parseInt(input) - 1)) {
					autoMobileVO.setVehicle_type(VehicleType.Small.getValue());
				} else if (VehicleType.Medium.ordinal() == (Integer.parseInt(input) - 1)) {
					autoMobileVO.setVehicle_type(VehicleType.Medium.getValue());
				} else if (VehicleType.Large.ordinal() == (Integer.parseInt(input) - 1)) {
					autoMobileVO.setVehicle_type(VehicleType.Large.getValue());
				}
				automobileMapper.update_ProductManagementModel(autoMobileVO);
				// productList.update(automobileInsurance);
				break;
			case 8:
				ArrayList<ServiceType> distinctServiceList = (ArrayList<ServiceType>)serviceTypeList.stream()
					.distinct()
					.collect(Collectors.toList());

				serviceMapper.delete_ProductManagementModel(autoMobileVO.getProduct_id());

				for (ServiceType serviceType : distinctServiceList) {
					ServiceVO serviceVO = new ServiceVO();
					serviceVO.setProduct_id(autoMobileVO.getProduct_id());
					serviceVO.setService(serviceType.getValue());
					serviceMapper.insert_ProductManagement(serviceVO);
				}
				break;
			default:
				break;
		}

	}

	public ArrayList<Product> getAll() {

		ArrayList<Insurance> insurances = new ArrayList<>();
		ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
		for (DiseaseVO diseaseVO : diseaseVOs) {
			Disease disease = new Disease();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			disease.setId(insuranceVO.getProduct_id());
			disease.setName(productVO.getName());
			disease.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			disease.setAgeRange(insuranceVO.getAge_range());
			disease.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(disease);
		}
		ArrayList<InjuryVO> injuryVOs = injuryMapper.getAllInjuryInsurance_SalesModel();
		for (InjuryVO injuryVO : injuryVOs) {
			Injury injury = new Injury();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(injuryVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			injury.setId(insuranceVO.getProduct_id());
			injury.setName(productVO.getName());
			injury.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			injury.setAgeRange(insuranceVO.getAge_range());
			injury.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(injury);
		}
		ArrayList<AutoMobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
		for (AutoMobileVO automobileVO : AutomobileVOs) {
			Automobile automobile = new Automobile();
			InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
			ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
			automobile.setId(insuranceVO.getProduct_id());
			automobile.setName(productVO.getName());
			automobile.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
			automobile.setAgeRange(insuranceVO.getAge_range());
			automobile.setMonthlyPremium(insuranceVO.getMonthly_premium());
			insurances.add(automobile);
		}

		return new ArrayList<>(insurances);
		// return productList.getAll();
	}
}
