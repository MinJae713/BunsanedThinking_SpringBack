package com.example.bunsanedthinking_springback.controller.customer;

import com.example.bunsanedthinking_springback.dto.customer.request.*;
import com.example.bunsanedthinking_springback.dto.customer.request.signUp.SignUpRequest;
import com.example.bunsanedthinking_springback.dto.customer.response.*;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.global.exception.*;
import com.example.bunsanedthinking_springback.model.service.customer.CustomerService;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerSModel;
	@PatchMapping("/applyEndorsement")
	public void applyEndorsement(@Range(min = 1, max = 31, message = "유효한 날짜가 아닙니다")
									 @RequestParam int index,
								 @RequestParam int contractId)
				throws NotExistContractException {
		customerSModel.applyEndorsement(index, contractId);
	}

	@PatchMapping("/applyInsuranceRevival")
	public void applyInsuranceRevival(@RequestParam int contractId)
            	throws NotExistContractException, NotExistTerminatedContract{
		customerSModel.applyInsuranceRevival(contractId);
	}

	@PatchMapping("/applyInsuranceTermination")
	public void applyInsuranceTermination(@RequestParam int contractId)
				throws NotExistContractException, NotExistMaintainedContract {
		customerSModel.applyInsuranceTermination(contractId);
	}

	@PatchMapping("/applyInsuranceRecontract")
	public void applyInsuranceRecontract(@RequestParam int contractId)
				throws NotExistContractException, NotExistExpiredContract {
		customerSModel.applyRecontract(contractId);
	}

	@PostMapping("/payInsurancefee")
	public void payInsurancefee(@RequestBody @Valid DepositRequest depositRequest)
			throws NotExistContractException, NotExistException {
		customerSModel.payInsurancefee(depositRequest);
	}
	@GetMapping("/getCustomerById")
	public Customer getCustomerById(@RequestParam int id) throws NotExistException {
		return customerSModel.getCustomerById(id);
	}

	@GetMapping("/getAllInsurance")
	public List<InsuranceListResponse> getAllInsurance() throws NotExistException {
		return customerSModel.getAllInsurance();
	}

	@GetMapping("/getAllDiseaseInsurance")
	public List<InsuranceListResponse> getAllDiseaseInsurance() {
		return customerSModel.getAllDiseaseInsurance();
	}

	@GetMapping("/getAllInjuryInsurance")
	public List<InsuranceListResponse> getAllInjuryInsurance() {
		return customerSModel.getAllInjuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public List<InsuranceListResponse> getAllAutomobileInsurance() {
		return customerSModel.getAllAutomobileInsurance();
	}

	@GetMapping("/getInsuranceByProductId")
	public Insurance getInsuranceByProductId(@RequestParam int id) throws NotExistException {
		return customerSModel.getInsuranceByProductId(id);
	}

    @GetMapping("/getInsuranceRowByProductId")
    public InsuranceListResponse getInsuranceRowByProductId(@RequestParam int id) throws NotExistException {
        return customerSModel.getInsuranceRowByProductId(id);
    }

	@GetMapping("/getAllLoan")
	public List<LoanListReponse> getAllLoan() {
		return customerSModel.getAllLoan();
	}

	@GetMapping("/getAllCollateralLoan")
	public List<LoanListReponse> getAllCollateralLoan() {
		return customerSModel.getAllCollateralLoan();
	}

	@GetMapping("/getAllFixedDepositLoan")
	public List<LoanListReponse> getAllFixedDepositLoan() {
		return customerSModel.getAllFixedDepositLoan();
	}

	@GetMapping("/getAllInsuranceContractLoan")
	public List<LoanListReponse> getAllInsuranceContractLoan() {
		return customerSModel.getAllInsuranceContractLoan();
	}

	@GetMapping("/getLoanByProductId")
	public Loan getLoanByProductId(@RequestParam int id) throws NotExistException {
		return customerSModel.getLoanByProductId(id);
	}

    @GetMapping("/getLoanRowByProductId")
    public LoanListReponse getLoanRowByProductId(@RequestParam int id) throws NotExistException {
        return customerSModel.getLoanRowByProductId(id);
    }

	@GetMapping("/getAllApprovedByCustomer")
	public List<Contract> getAllApprovedByCustomer() {
		return customerSModel.getAllApprovedByCustomer();
	}

	@GetMapping("/getAllContractByCustomerId")
	public List<ManagementContractResponse> getAllContractByCustomerId(@RequestParam int customerId) {
		return customerSModel.getAllContractByCustomerId(customerId);
	}

	@GetMapping("/getAllAutomobileContractByCustomerId")
	public List<ManagementContractResponse> getAllAutomobileContractByCustomerId(@RequestParam int customerId) {
		return customerSModel.getAllAutomobileContractByCustomerId(customerId);
	}

	@GetMapping("/getAllInjuryContractByCustomerId")
	public List<ManagementContractResponse> getAllInjuryContractByCustomerId(@RequestParam int customerId) {
		return customerSModel.getAllInjuryContractByCustomerId(customerId);
	}

	@GetMapping("/getAllDiseaseContractByCustomerId")
	public List<ManagementContractResponse> getAllDiseaseContractByCustomerId(@RequestParam int customerId) {
		return customerSModel.getAllDiseaseContractByCustomerId(customerId);
	}

	@GetMapping("/getAllContractByProductId")
	public List<Contract> getAllContractByProductId(@RequestParam int id) {
		return customerSModel.getAllContractByProductId(id);
	}

	@GetMapping("/getContractById")
	public ManagementContractResponse getContractById(@RequestParam int id, @RequestParam int customerId) throws NotExistContractException, NotExistException {
		return customerSModel.getContractRowById(id, customerId);
	}

	@GetMapping("/getContractRowById")
	public ManagementContractResponse getContractRowById(@RequestParam int id, @RequestParam int customerId) throws NotExistContractException, NotExistException {
		return customerSModel.getContractRowById(id, customerId);
	}

	@GetMapping("/getContractByOneAutomobileId")
	public Contract getContractByOneAutomobileId(@RequestParam int id) throws NotExistContractException {
		return customerSModel.getContractByOneAutomobileId(id);
	}

	@GetMapping("/getAllAccidentByCustomerId")
	public List<ViewAccidentResponse> getAllAccidentByCustomerId(@RequestParam int id) {
		return customerSModel.getAllAccidentByCustomerId(id);
    }
	@GetMapping("/getAccidentById")
	public Accident getAccidentById(@RequestParam int id, @RequestParam int customerId) throws NotExistException, IllegalArgumentException {
		return customerSModel.getAccidentById(id, customerId);
	}
	@GetMapping("/getAccidentRowById")
	public ViewAccidentResponse getAccidentRowById(@RequestParam int id, @RequestParam int customerId) throws NotExistException, IllegalArgumentException {
		return customerSModel.getAccidentRowById(id, customerId);
	}
	@GetMapping("/getAllComplaintsByCustomerId")
	public List<ViewComplaintResponse> getAllComplaintsByCustomerId(@RequestParam int id) {
		return customerSModel.getAllComplaintsByCustomerId(id);
	}

	@GetMapping("/getComplaintById")
	public Complaint getComplaintById(@RequestParam int id, @RequestParam int customerId) throws NotExistException, IllegalArgumentException {
		return customerSModel.getComplaintById(id, customerId);
	}
	@GetMapping("/getComplaintRowById")
	public ViewComplaintResponse getComplaintRowById(@RequestParam int id, @RequestParam int customerId) throws NotExistException, IllegalArgumentException {
		return customerSModel.getComplaintRowById(id, customerId);
	}
	@PostMapping("/signUp")
	public void signUp(@RequestBody @Valid SignUpRequest signUpRequest) throws DuplicateResidentRegistrationNumberException, ParseException {
		customerSModel.signUp(signUpRequest);
	}

	@PostMapping("/askInsuranceCounsel")
	public void askInsuranceCounsel(@RequestBody AskInsuranceCounselRequest askInsuranceCounselRequest) throws NotExistException {
		customerSModel.askInsuranceCounsel(askInsuranceCounselRequest);
	}

	@PostMapping("/buyInsurance")
	public void buyInsurance(@RequestBody BuyInsuranceRequest buyInsuranceRequest) throws NotExistException, IllegalArgumentException {
		customerSModel.buyInsurance(buyInsuranceRequest);
	}

	@PostMapping("/complain")
	public void complain(@RequestBody @Valid ComplainRequest complainRequest) throws NotExistException {
		customerSModel.complain(complainRequest);
	}

	@PostMapping("/loan")
	public void loan(@RequestBody LoanRequest loanRequest) throws AlreadyRequestingException, NotExistException {
		customerSModel.loan(loanRequest);
	}

	@PostMapping("/receiveInsurance")
	public void receiveInsurance(@RequestParam int contractId,
								 @RequestParam MultipartFile medicalCertificate,
								 @RequestParam MultipartFile receipt,
								 @RequestParam MultipartFile residentRegistrationCard)
            throws NotExistContractException, NotExistException, IOException {
		ReceiveInsuranceRequest receiveInsuranceRequest = new ReceiveInsuranceRequest();
		receiveInsuranceRequest.setContractId(contractId);
		receiveInsuranceRequest.setMedicalCertificate(medicalCertificate);
		receiveInsuranceRequest.setReceipt(receipt);
		receiveInsuranceRequest.setResidentRegistrationCard(residentRegistrationCard);
		customerSModel.receiveInsurance(receiveInsuranceRequest);
	}

	@PostMapping("/reportAccident")
	public void reportAccident(@RequestBody @Valid ReportAccidentRequest reportAccidentRequest) throws NotExistException {
		customerSModel.reportAccident(reportAccidentRequest);
	}

	@GetMapping("/isAutomobileContract")
	public boolean isAutomobileContract(@RequestParam int id)
			throws NotExistContractException, IllegalArgumentException {
		return customerSModel.isAutomobileContract(id);
	}
}
