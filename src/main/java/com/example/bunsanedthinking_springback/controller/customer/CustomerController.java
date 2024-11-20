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
	public List<InsuranceListResponse> getAllInsurance() {
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
	public List<Contract> getAllApprovedByCustomer() throws NotExistContractException, NotExistException {
		return customerSModel.getAllApprovedByCustomer();
	}

	@GetMapping("/getAllContractByCustomerId")
	public List<ManagementContractResponse> getAllContractByCustomerId(@RequestParam int customerId) throws NotExistContractException, NotExistException {
		return customerSModel.getAllContractByCustomerId(customerId);
	}

	@GetMapping("/getAllAutomobileContractByCustomerId")
	public List<ManagementContractResponse> getAllAutomobileContractByCustomerId(@RequestParam int customerId) throws NotExistContractException, NotExistException {
		return customerSModel.getAllAutomobileContractByCustomerId(customerId);
	}

	@GetMapping("/getAllInjuryContractByCustomerId")
	public List<ManagementContractResponse> getAllInjuryContractByCustomerId(@RequestParam int customerId) throws NotExistContractException, NotExistException {
		return customerSModel.getAllInjuryContractByCustomerId(customerId);
	}

	@GetMapping("/getAllDiseaseContractByCustomerId")
	public List<ManagementContractResponse> getAllDiseaseContractByCustomerId(@RequestParam int customerId) throws NotExistContractException, NotExistException {
		return customerSModel.getAllDiseaseContractByCustomerId(customerId);
	}

	@GetMapping("/getAllContractByProductId")
	public List<Contract> getAllContractByProductId(@RequestParam int id) throws NotExistContractException, NotExistException {
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
	public Contract getContractByOneAutomobileId(@RequestParam int id) throws NotExistContractException, NotExistException {
		return customerSModel.getContractByOneAutomobileId(id);
	}

	@GetMapping("/getAllAccidentByCustomerId")
	public List<ViewAccidentResponse> getAllAccidentByCustomerId(@RequestParam int id) throws NotExistException {
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
	public List<ViewComplaintResponse> getAllComplaintsByCustomerId(@RequestParam int id) throws NotExistException {
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
