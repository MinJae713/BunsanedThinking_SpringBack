package com.example.bunsanedthinking_springback.controller.customer;

import com.example.bunsanedthinking_springback.dto.customer.request.*;
import com.example.bunsanedthinking_springback.dto.customer.response.*;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.global.exception.*;
import com.example.bunsanedthinking_springback.model.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerSModel;
	@PatchMapping("/applyEndorsement")
	public void applyEndorsement(@RequestParam int index, @RequestParam int contractId)
				throws NotExistContractException, NotExistException {
		customerSModel.applyEndorsement(index, contractId);
	}

	@PatchMapping("/applyInsuranceRevival")
	public void applyInsuranceRevival(@RequestParam int contractId)
            	throws NotExistContractException, NotExistTerminatedContract, NotExistException {
		customerSModel.applyInsuranceRevival(contractId);
	}

	@PatchMapping("/applyInsuranceTermination")
	public void applyInsuranceTermination(@RequestParam int contractId)
				throws NotExistContractException, NotExistMaintainedContract, NotExistException {
		customerSModel.applyInsuranceTermination(contractId);
	}

	@PatchMapping("/applyInsuranceRecontract")
	public void applyInsuranceRecontract(@RequestParam int contractId)
				throws NotExistContractException, NotExistExpiredContract, NotExistException {
		customerSModel.applyRecontract(contractId);
	}

	@PostMapping("/payInsurancefee")
	public void payInsurancefee(@RequestBody DepositDTO depositDTO)
			throws NotExistContractException, NotExistException {
		customerSModel.payInsurancefee(depositDTO);
	}
	@GetMapping("/getCustomerById")
	// http://localhost:8080/customer/getCustomerById?id=2002 - Get Post Patch delete
	public Customer getCustomerById(@RequestParam int id) throws NotExistException, NotExistContractException {
		return customerSModel.getCustomerById(id);
	}

	@GetMapping("/getAllInsurance")
	public List<GetAllInsuranceResponse> getAllInsurance() {
		return customerSModel.getAllInsurance();
	}

	@GetMapping("/getAllDiseaseInsurance")
	public List<GetAllInsuranceResponse> getAllDiseaseInsurance() {
		return customerSModel.getAllDiseaseInsurance();
	}

	@GetMapping("/getAllInjuryInsurance")
	public List<GetAllInsuranceResponse> getAllInjuryInsurance() {
		return customerSModel.getAllInjuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public List<GetAllInsuranceResponse> getAllAutomobileInsurance() {
		return customerSModel.getAllAutomobileInsurance();
	}

	@GetMapping("/getInsuranceByProductId")
	public Insurance getInsuranceByProductId(@RequestParam int id) throws NotExistException {
		return customerSModel.getInsuranceByProductId(id);
	}

	@GetMapping("/getAllLoan")
	public List<GetAllLoanReponse> getAllLoan() {
		return customerSModel.getAllLoan();
	}

	@GetMapping("/getAllCollateralLoan")
	public List<GetAllLoanReponse> getAllCollateralLoan() {
		return customerSModel.getAllCollateralLoan();
	}

	@GetMapping("/getAllFixedDepositLoan")
	public List<GetAllLoanReponse> getAllFixedDepositLoan() {
		return customerSModel.getAllFixedDepositLoan();
	}

	@GetMapping("/getAllInsuranceContractLoan")
	public List<GetAllLoanReponse> getAllInsuranceContractLoan() {
		return customerSModel.getAllInsuranceContractLoan(null);
	}

	@GetMapping("/getLoanByProductId")
	public Loan getLoanByProductId(@RequestParam int id) throws NotExistException {
		return customerSModel.getLoanByProductId(id);
	}

	@GetMapping("/getAllApprovedByCustomer")
	public List<Contract> getAllApprovedByCustomer() throws NotExistContractException, NotExistException {
		return customerSModel.getAllApprovedByCustomer();
	}

	@GetMapping("/getAllContractByCustomerId")
	public List<GetAllContractByCustomerIdResponse> getAllContractByCustomerId(@RequestParam int id) throws NotExistContractException, NotExistException {
		return customerSModel.getAllContractByCustomerId(id);
	}

	@GetMapping("/getAllAutomobileInsuranceContract")
	public List<Contract> getAllAutomobileInsuranceContract() throws NotExistContractException, NotExistException {
		return customerSModel.getAllAutomobileInsuranceContract();
	}

	@GetMapping("/getAllInjuryInsuranceContract")
	public List<Contract> getAllInjuryInsuranceContract() throws NotExistContractException, NotExistException {
		return customerSModel.getAllInjuryInsuranceContract();
	}

	@GetMapping("/getAllDiseaseInsuranceContract")
	public List<Contract> getAllDiseaseInsuranceContract() throws NotExistContractException, NotExistException {
		return customerSModel.getAllDiseaseInsuranceContract();
	}

	@GetMapping("/getAllContractByProductId")
	public List<Contract> getAllContractByProductId(@RequestParam int id) throws NotExistContractException, NotExistException {
		return customerSModel.getAllContractByProductId(id);
	}

	@GetMapping("/getContractById")
	public Contract getContractById(@RequestParam int id) throws NotExistContractException, NotExistException {
		return customerSModel.getContractById(id);
	}

	@GetMapping("/getContractByOneAutomobileId")
	public Contract getContractByOneAutomobileId(@RequestParam int id) throws NotExistContractException, NotExistException {
		return customerSModel.getContractByOneAutomobileId(id);
	}

	@GetMapping("/getAllAccidentByCustomerId")
	public List<GetAllAccidentByCustomerIdResponse> getAllAccidentByCustomerId(@RequestParam int id) throws NotExistException {
		return customerSModel.getAllAccidentByCustomerId(id);
    }
	@GetMapping("/getAccidentById")
	public Accident getAccidentById(@RequestParam int id) throws NotExistException {
		return customerSModel.getAccidentById(id);
	}

	@GetMapping("/getAllComplaintsByCustomerId")
	public List<GetAllComplaintsByCustomerIdResponse> getAllComplaintsByCustomerId(@RequestParam int id) throws NotExistException {
		return customerSModel.getAllComplaintsByCustomerId(id);
	}

	@GetMapping("/getComplaintById")
	public Complaint getComplaintById(@RequestParam int id) throws NotExistException {
		return customerSModel.getComplaintById(id);
	}

	@PostMapping("/signUp")
	public void signUp(@RequestBody SignUpDTO signUpDTO) throws DuplicateResidentRegistrationNumberException {
		customerSModel.signUp(signUpDTO);
	}

	@PostMapping("/askInsuranceCounsel")
	public void askInsuranceCounsel(@RequestBody AskInsuranceCounselDTO askInsuranceCounselDTO) throws NotExistException {
		customerSModel.askInsuranceCounsel(askInsuranceCounselDTO);
	}

	@PostMapping("/buyInsurance")
	public void buyInsurance(@RequestBody BuyInsuranceDTO buyInsuranceDTO) throws NotExistException {
		customerSModel.buyInsurance(buyInsuranceDTO);
	}

	@PostMapping("/complain")
	public void complain(@RequestBody ComplainDTO complainDTO) throws NotExistException {
		customerSModel.complain(complainDTO);
	}

	@PostMapping("/loan")
	public void loan(@RequestBody LoanDTO loanDTO) throws AlreadyRequestingException, NotExistException {
		customerSModel.loan(loanDTO);
	}

	// 얜 검증 미완
	@PostMapping("/receiveInsurance")
	public void receiveInsurance(@RequestBody ReceiveInsuranceDTO receiveInsuranceDTO) throws NotExistContractException, NotExistException {
		customerSModel.receiveInsurance(receiveInsuranceDTO);
	}

	@PostMapping("/reportAccident")
	public void reportAccident(@RequestBody ReportAccidentDTO reportAccidentDTO) throws NotExistException {
		customerSModel.reportAccident(reportAccidentDTO);
	}
}
