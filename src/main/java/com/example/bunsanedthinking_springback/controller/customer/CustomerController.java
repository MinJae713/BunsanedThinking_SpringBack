package com.example.bunsanedthinking_springback.controller.customer;

import com.example.bunsanedthinking_springback.dto.customer.DepositDTO;
import com.example.bunsanedthinking_springback.entity.accident.Accident;
import com.example.bunsanedthinking_springback.entity.complaint.Complaint;
import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.insurance.Automobile;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.global.exception.*;
import com.example.bunsanedthinking_springback.model.service.customer.CustomerSModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerSModel customerSModel;
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
	// get은 검증 완
	@GetMapping("/getCustomerById")
	// http://localhost:8080/customer/getCustomerById?id=2002 - Get Post Patch delete
	public Customer getCustomerById(@RequestParam int id) throws NotExistException, NotExistContractException {
		return customerSModel.getCustomerById(id);
	}

	@GetMapping("/getAllInsurance")
	public List<Insurance> getAllInsurance() {
		return customerSModel.getAllInsurance();
	}

	@GetMapping("/getAllDiseaseInsurance")
	public List<Disease> getAllDiseaseInsurance() {
		return customerSModel.getAllDiseaseInsurance();
	}

	@GetMapping("/getAllInjuryInsurance")
	public List<Injury> getAllInjuryInsurance() {
		return customerSModel.getAllInjuryInsurance();
	}

	@GetMapping("/getAllAutomobileInsurance")
	public List<Automobile> getAllAutomobileInsurance() {
		return customerSModel.getAllAutomobileInsurance();
	}

	@GetMapping("/getInsuranceByProductId")
	public Insurance getInsuranceByProductId(@RequestParam int id) throws NotExistException {
		return customerSModel.getInsuranceByProductId(id);
	}

	@GetMapping("/getAllLoan")
	public List<Loan> getAllLoan() {
		return customerSModel.getAllLoan();
	}

	@GetMapping("/getAllCollateralLoan")
	public List<Collateral> getAllCollateralLoan() {
		return customerSModel.getAllCollateralLoan();
	}

	@GetMapping("/getAllFixedDepositLoan")
	public List<FixedDeposit> getAllFixedDepositLoan() {
		return customerSModel.getAllFixedDepositLoan();
	}

	@GetMapping("/getAllInsuranceContractLoan")
	public List<InsuranceContract> getAllInsuranceContractLoan() {
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
	public List<Contract> getAllContractByCustomerId(@RequestParam int id) throws NotExistContractException, NotExistException {
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
	public List<Accident> getAllAccidentByCustomerId(@RequestParam int id) throws NotExistException {
		return customerSModel.getAllAccidentByCustomerId(id);
    }
	@GetMapping("/getAccidentById")
	public Accident getAccidentById(@RequestParam int id) throws NotExistException {
		return customerSModel.getAccidentById(id);
	}

	@GetMapping("/getAllComplaintsByCustomerId")
	public List<Complaint> getAllComplaintsByCustomerId(@RequestParam int id) throws NotExistException {
		return customerSModel.getAllComplaintsByCustomerId(id);
	}

	@GetMapping("/getComplaintById")
	public Complaint getComplaintById(@RequestParam int id) throws NotExistException {
		return customerSModel.getComplaintById(id);
	}
}
