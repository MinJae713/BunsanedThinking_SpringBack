package com.example.bunsanedthinking_springback.model.productManagement;

import static com.example.bunsanedthinking_springback.entity.loan.LoanType.*;

import com.example.bunsanedthinking_springback.entity.insurance.*;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.entity.product.ProductList;
import com.example.bunsanedthinking_springback.exception.DuplicateInsuranceException;
import com.example.bunsanedthinking_springback.exception.NotExistException;
import com.example.bunsanedthinking_springback.repository.AutomobileMapper;
import com.example.bunsanedthinking_springback.repository.DiseaseMapper;
import com.example.bunsanedthinking_springback.repository.InjuryMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.repository.ServiceMapper;
import com.example.bunsanedthinking_springback.vo.AutomobileVO;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import com.example.bunsanedthinking_springback.vo.ServiceVO;

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
	ProductMapper productMapper;

	@Autowired
	InsuranceMapper insuranceMapper;
	@Autowired
	DiseaseMapper diseaseMapper;
	@Autowired
	AutomobileMapper automobileMapper;
	@Autowired
	ServiceMapper serviceMapper;
	@Autowired
	InjuryMapper injuryMapper;

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
		productMapper.delete_ProductManagementModel(id);
		// productList.delete(id);
	}

	public Insurance getInsuranceProduct(ProductList productList, int id) throws NotExistException {
		try {
			Insurance insurance = null;

			ArrayList<DiseaseVO> diseaseVOs = diseaseMapper.getAllDiseaseInsurance_SalesModel();
			for (DiseaseVO diseaseVO : diseaseVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(diseaseVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());
				if(productVO.getId() == id){
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
				if(productVO.getId() == id){
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
			ArrayList<AutomobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
			for (AutomobileVO automobileVO : AutomobileVOs) {
				InsuranceVO insuranceVO = insuranceMapper.get_SalesModel(automobileVO.getProduct_id());
				ProductVO productVO = productMapper.get_SalesModel(insuranceVO.getProduct_id());

				if(productVO.getId() == id){
					Automobile automobile = new Automobile();

					automobile.setId(insuranceVO.getProduct_id());
					automobile.setName(productVO.getName());
					automobile.setInsuranceType(InsuranceType.fromInt(insuranceVO.getInsurance_type()));
					automobile.setAgeRange(insuranceVO.getAge_range());
					automobile.setCoverage(insuranceVO.getCoverage());
					automobile.setMonthlyPremium(insuranceVO.getMonthly_premium());
					automobile.setContractPeriod(insuranceVO.getContract_period());

					automobile.setAccidentLimit(automobileVO.getAccident_limit());
					automobile.setVehicleType(VehicleType.fromInt(automobileVO.getVerhicle_type()));
					ArrayList<ServiceType> serviceTypes = new ArrayList<>();
					ArrayList<ServiceVO> serviceVOs = serviceMapper.get_SalesModel(id);
					for (ServiceVO serviceVO :serviceVOs)
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
		ArrayList<AutomobileVO> AutomobileVOs = automobileMapper.getAllAutomobileInsurance_SalesModel();
		for (AutomobileVO automobileVO : AutomobileVOs) {
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