package com.example.bunsanedthinking_springback.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.employee.sales.request.AddDiseaseHistoryRequest;
import com.example.bunsanedthinking_springback.dto.employee.sales.request.InduceInsuranceProductRequest;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.GetAllCounselResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.GetAllInsuranceProductResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.GetAllSalesResponse;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.employee.Sales;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.model.service.employee.sales.SalesService;

@RestController
@RequestMapping("/employee/sales")
public class SalesController {
	@Autowired
	private SalesService salesSModel;

	@PatchMapping("/evaluateSalesPerformance")
	public void evaluateSalesPerformance(int evaluate, int id){
		salesSModel.evaluateSalesPerformance(evaluate, id);
	}

	@PatchMapping("/handleInsuranceConsultation")
	public void handleInsuranceConsultation(int id) throws AlreadyProcessedException {
		salesSModel.handleInsuranceConsultation(id);
	}

	@PostMapping("/induceInsuranceProduct")
	public Customer induceInsuranceProduct(@RequestBody InduceInsuranceProductRequest induceInsuranceProductRequest) {
		return salesSModel.induceInsuranceProduct(induceInsuranceProductRequest);
	}

	@PostMapping("/induceLoanProduct")
	public Customer induceLoanProduct(@RequestBody InduceInsuranceProductRequest induceInsuranceProductRequest) {
		return salesSModel.induceLoanProduct(induceInsuranceProductRequest);
	}

	@GetMapping("/getInsuranceProduct")
	public Insurance getInsuranceProduct(int id) {
		return salesSModel.getInsuranceProduct(id);
	}

	@GetMapping("/getLoanProduct")
	public Loan getLoanProduct(int id) {
		return salesSModel.getLoanProduct(id);
	}

	@GetMapping("/getAllSales")
	public List<GetAllSalesResponse> getAllSales() {
		return salesSModel.getAllSales();
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(int id) {
		return salesSModel.getEmployee(id);
	}

	@GetMapping("/getSales")
	public Sales getSales(int id) {
		return salesSModel.getSales(id);
	}

	@GetMapping("/getAllCounsel")
	public List<GetAllCounselResponse> getAllCounsel() {
		return salesSModel.getAllCounsel();
	}

	@GetMapping("/getCounsel")
	public Counsel getCounsel(int id) {
		return salesSModel.getCounsel(id);
	}

	@GetMapping("/getAllInsuranceProduct")
	public List<GetAllInsuranceProductResponse> getAllInsuranceProduct() {
		return salesSModel.getAllInsuranceProduct();
	}

	@GetMapping("/getAllLoanProduct")
	public List<Loan> getAllLoanProduct() {
		return salesSModel.getAllLoanProduct();
	}

	@PostMapping("/addDiseaseHistory")
	public DiseaseHistory addDiseaseHistory(@RequestBody AddDiseaseHistoryRequest addDiseaseHistoryRequest) {
		return salesSModel.addDiseaseHistory(addDiseaseHistoryRequest);
	}

	@PatchMapping("/updateContractCount")
	public void updateContractCount(int id, int contractCount) {
		salesSModel.updateContractCount(id, contractCount);
	}

	@GetMapping("/getAllDiseaseInsurance")
	public List<Disease> getAllDiseaseInsurance() {
		return salesSModel.getAllDiseaseInsurance();
	}

	@GetMapping("/getAllInjuryInsurance")
	public List<Injury> getAllInjuryInsurance() {
		return salesSModel.getAllInjuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public List<Automobile> getAllAutomobileInsurance() {
		return salesSModel.getAllAutomobileInsurance();
	}

	@GetMapping("getAllCollateralLoan")
	public List<Collateral> getAllCollateralLoan() {
		return salesSModel.getAllCollateralLoan();
	}

	@GetMapping("/getAllFixedDepositLoan")
	public List<FixedDeposit> getAllFixedDepositLoan() {
		return salesSModel.getAllFixedDepositLoan();
	}

	@GetMapping("/getAllInsuranceContractLoan")
	public List<InsuranceContract> getAllInsuranceContractLoan() {
		return salesSModel.getAllInsuranceContractLoan();
	}

	@GetMapping("/getSalesContractCount")
	public int getSalesContractCount(int id) {
		return salesSModel.getSalesContractCount(id);
	}

	@PatchMapping("/setContractCount")
	public void setContractCount(int contractCount, int id) {
		salesSModel.setContractCount(contractCount, id);
	}
}
