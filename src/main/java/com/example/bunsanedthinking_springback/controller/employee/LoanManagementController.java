package com.example.bunsanedthinking_springback.controller.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bunsanedthinking_springback.dto.employee.loanManagement.AddCollateralLoanProductDTO;
import com.example.bunsanedthinking_springback.dto.employee.loanManagement.AddFixesDepositLoanProductDTO;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.global.exception.AlreadyProcessedException;
import com.example.bunsanedthinking_springback.global.exception.DuplicateLoanException;
import com.example.bunsanedthinking_springback.global.exception.NotExistContractException;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.employee.loanManagement.LoanManagementService;

@RestController
@RequestMapping("/employee/loanManagement")
public class LoanManagementController {
	@Autowired
	private LoanManagementService loanManagementService;

	@PostMapping("/addCollateralProduct")
	public void addLoanProduct(@RequestBody AddCollateralLoanProductDTO addCollateralLoanProductDTO) throws DuplicateLoanException {
		loanManagementService.addLoanProduct(addCollateralLoanProductDTO);
	}

	@PostMapping("/addLoanProduct")
	public void addLoanProduct(@RequestBody AddFixesDepositLoanProductDTO addFixesDepositLoanProductDTO) throws DuplicateLoanException {
		loanManagementService.addLoanProduct(addFixesDepositLoanProductDTO);
	}

	@GetMapping("/getLoanProduct")
	public Loan getLoanProduct(@RequestParam("id") int id) throws NotExistException {
		return loanManagementService.getLoanProduct(id);
	}

	public boolean collectLoanPrincipalInterest() {
		return loanManagementService.collectLoanPrincipalInterest();
	}

	@PostMapping("/requestLoan")
	public void requestLoan(@RequestParam("contractId") int contractId,
		@RequestParam("money") int money, @RequestParam("paymentType") int paymentType,
		@RequestParam("result") boolean result)
		throws AlreadyProcessedException, NotExistContractException {
		loanManagementService.requestLoan(contractId, money, paymentType, result);
	}

	@PatchMapping("/updateLoanProduct")
	public void updateLoanProduct(@RequestParam("index") int index, @RequestParam("input") String input,
		@RequestParam("loanId") int loanId) throws DuplicateLoanException, NotExistException {
		loanManagementService.updateLoanProduct(index, input, loanId);
	}

	@DeleteMapping("/deleteLoanProduct")
	public void deleteLoanProduct(@RequestParam("id") int id) throws NotExistException {
		loanManagementService.deleteLoanProduct(id);
	}

	@GetMapping("/getAll")
	public List<Loan> getAll() {
		return loanManagementService.getAll();
	}

	@GetMapping("/getOutcome")
	public double getOutcome(@RequestParam("contractId") int contractId) throws
		NotExistContractException, NotExistException {
		return loanManagementService.getOutcome(contractId);
	}
}
