package com.example.bunsanedthinking_springback.model.entityModel.loan;

import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.collateral.CollateralEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.fixedDeposit.FixedDepositEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insuranceContract.InsuranceContractEntityModel;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanEntityModel {
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private CollateralEntityModel collateralEntityModel;
	@Autowired
	private FixedDepositEntityModel fixedDepositEntityModel;
	@Autowired
	private InsuranceContractEntityModel insuranceContractEntityModel;
	@ReadOnly
	public Loan getById(int id) {
		Loan loan = collateralEntityModel.getById(id);
		if (loan != null)
			return loan;
		loan = fixedDepositEntityModel.getById(id);
		if (loan != null)
			return loan;
		loan = insuranceContractEntityModel.getById(id);
		return loan;
	}
	@ReadOnly
	public List<Loan> getAll() {
		List<Loan> loans = new ArrayList<Loan>();
		loanMapper.getAll()
			.forEach(e -> loans.add(getById(e.getProduct_id())));
		return loans;
	}
	@ReadOnly
	public Integer getMaxId() {
		return loanMapper.getMaxId();
	}

	public void add(Loan loan) {
		if (loan == null) return;
		else if (loan instanceof Collateral) collateralEntityModel.add((Collateral) loan);
		else if (loan instanceof FixedDeposit) fixedDepositEntityModel.add((FixedDeposit) loan);
		else if (loan instanceof InsuranceContract) insuranceContractEntityModel.add((InsuranceContract) loan);
	}

	public void update(Loan loan) {
		if (loan == null) return;
		else if (loan instanceof Collateral) collateralEntityModel.update((Collateral) loan);
		else if (loan instanceof FixedDeposit) fixedDepositEntityModel.update((FixedDeposit) loan);
		else if (loan instanceof InsuranceContract) insuranceContractEntityModel.update((InsuranceContract) loan);
	}

	public void delete(int id) {
		Loan loan = getById(id);
		if (loan == null) return;
		else if (loan instanceof Collateral) collateralEntityModel.delete(id);
		else if (loan instanceof FixedDeposit) fixedDepositEntityModel.delete(id);
		else if (loan instanceof InsuranceContract) insuranceContractEntityModel.delete(id);
	}
}
