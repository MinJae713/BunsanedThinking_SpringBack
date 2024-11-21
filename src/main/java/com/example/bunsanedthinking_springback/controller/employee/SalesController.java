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
import com.example.bunsanedthinking_springback.dto.employee.sales.response.EvaluateSalesPerformanceDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.EvaluateSalesPerformanceResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.HandleInsuranceConsultationDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.HandleInsuranceConsultationResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductDetailResponse.InduceInsuranceProductDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceInsuranceProductResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductDetailResponse.InduceLoanProductDetailResponse;
import com.example.bunsanedthinking_springback.dto.employee.sales.response.InduceLoanProductResponse;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.diseaseHistory.DiseaseHistory;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
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
	public InduceInsuranceProductResponse getInsuranceProduct(int id) {
		return salesSModel.getInsuranceProduct(id);
	}

	@GetMapping("/getInsuranceProductDetail")
	public InduceInsuranceProductDetailResponse getInsuranceProductDetail(int id) throws NotExistException {
		return salesSModel.getInsuranceProductDetail(id);
	}

	@GetMapping("/getLoanProduct")
	public InduceLoanProductResponse getLoanProduct(int id) {
		return salesSModel.getLoanProduct(id);
	}

	@GetMapping("/getLoanProductDetail")
	public InduceLoanProductDetailResponse getLoanProductDetail(int id) throws NotExistException {
		return salesSModel.getLoanProductDetail(id);
	}

	@GetMapping("/getAllSales")
	public List<EvaluateSalesPerformanceResponse> getAllSales() {
		return salesSModel.getAllSales();
	}

	@GetMapping("/getEmployee")
	public Employee getEmployee(int id) {
		return salesSModel.getEmployee(id);
	}

	@GetMapping("/getSales")
	public EvaluateSalesPerformanceResponse getSales(int id) {
		return salesSModel.getSales(id);
	}

	@GetMapping("/getSalesDetail")
	public EvaluateSalesPerformanceDetailResponse getSalesDetail(int id) {
		return salesSModel.getSalesDetail(id);
	}

	@GetMapping("/getAllCounsel")
	public List<HandleInsuranceConsultationResponse> getAllCounsel() {
		return salesSModel.getAllCounsel();
	}

	@GetMapping("/getCounsel")
	public HandleInsuranceConsultationResponse getCounsel(int id) {
		return salesSModel.getCounsel(id);
	}

	@GetMapping("/getCounselDetail")
	public HandleInsuranceConsultationDetailResponse getCounselDetail(int id) {
		return salesSModel.getCounselDetail(id);
	}

	@GetMapping("/getAllCompletedCounsel")
	public List<HandleInsuranceConsultationResponse> getAllCompletedCounsel() {
		return salesSModel.getAllCompletedCounsel();
	}

	@GetMapping("/getAllUnprocessedCounsel")
	public List<HandleInsuranceConsultationResponse> getAllUnprocessedCounsel() {
		return salesSModel.getAllUnprocessedCounsel();
	}

	@GetMapping("/getAllInsuranceProduct")
	public List<InduceInsuranceProductResponse> getAllInsuranceProduct() {
		return salesSModel.getAllInsuranceProduct();
	}

	@GetMapping("/getAllLoanProduct")
	public List<InduceLoanProductResponse> getAllLoanProduct() {
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
	public List<InduceInsuranceProductResponse> getAllDiseaseInsurance() {
		return salesSModel.getAllDiseaseInsurance();
	}

	@GetMapping("/getAllInjuryInsurance")
	public List<InduceInsuranceProductResponse> getAllInjuryInsurance() {
		return salesSModel.getAllInjuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public List<InduceInsuranceProductResponse> getAllAutomobileInsurance() {
		return salesSModel.getAllAutomobileInsurance();
	}

	@GetMapping("getAllCollateralLoan")
	public List<InduceLoanProductResponse> getAllCollateralLoan() {
		return salesSModel.getAllCollateralLoan();
	}

	@GetMapping("/getAllFixedDepositLoan")
	public List<InduceLoanProductResponse> getAllFixedDepositLoan() {
		return salesSModel.getAllFixedDepositLoan();
	}

	@GetMapping("/getAllInsuranceContractLoan")
	public List<InduceLoanProductResponse> getAllInsuranceContractLoan() {
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
