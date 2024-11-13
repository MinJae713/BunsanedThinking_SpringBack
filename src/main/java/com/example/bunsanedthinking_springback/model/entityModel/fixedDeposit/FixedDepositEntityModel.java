package com.example.bunsanedthinking_springback.model.entityModel.fixedDeposit;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.endorsement.EndorsementEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.recontract.RecontractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.revival.RevivalEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.termination.TerminationEntityModel;
import com.example.bunsanedthinking_springback.repository.FixedDepositMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixedDepositEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private FixedDepositMapper fixedDepositMapper;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private EndorsementEntityModel endorsementEntityModel;
	@Autowired
	private RevivalEntityModel revivalEntityModel;
	@Autowired
	private TerminationEntityModel terminationEntityModel;
	@Autowired
	private RecontractEntityModel recontractEntityModel;


	public FixedDeposit getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		LoanVO loanVO = loanMapper.getById(id).orElse(null);
		if (loanVO == null)
			return null;
		FixedDepositVO fixedDepositVO = fixedDepositMapper.getById(id).orElse(null);
		if (fixedDepositVO == null)
			return null;
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getProductId() == id).toList();
		return fixedDepositVO.getEntity(productVO, loanVO, contracts);
//		return new FixedDeposit(productVO, loanVO, minimumAmount);
	}

	public List<FixedDeposit> getAll() {
		List<FixedDeposit> fixedDeposits = new ArrayList<FixedDeposit>();
		fixedDepositMapper.getAll()
			.forEach(e -> fixedDeposits.add(getById(e.getProduct_id())));
		return fixedDeposits;
	}

	public Integer getMaxId() {
		return fixedDepositMapper.getMaxId();
	}

	public void add(FixedDeposit fixedDeposit) {
		if (fixedDeposit == null) return;
		if (fixedDepositMapper.getById(fixedDeposit.getId()).isPresent()) return;
		productMapper.insert(fixedDeposit.findProductVO());
		loanMapper.insert(fixedDeposit.findLoanVO());
		fixedDepositMapper.insert(fixedDeposit.findVO());
		if (fixedDeposit.getContractList() == null) return;
		fixedDeposit.getContractList().forEach(e -> contractEntityModel.add(e));
	}

	public void update(FixedDeposit fixedDeposit) {
		if (fixedDeposit == null) return;
		if (fixedDepositMapper.getById(fixedDeposit.getId()).isEmpty()) return;
		List<Contract> contracts = fixedDeposit.getContractList();
		if (contracts != null) contracts.forEach(e -> contractEntityModel.update(e));
		fixedDepositMapper.update(fixedDeposit.findVO());
		loanMapper.update(fixedDeposit.findLoanVO());
		productMapper.update(fixedDeposit.findProductVO());
	}

	public void delete(int id) {
		if (fixedDepositMapper.getById(id).isEmpty()) return;
		contractEntityModel.getAll().stream().
				filter(e -> e.getProductId() == id).
				forEach(e -> deleteContract(e));
		fixedDepositMapper.deleteById(id);
		loanMapper.deleteById(id);
		productMapper.deleteById(id);
	}

	private void deleteContract(Contract contract) {
		endorsementEntityModel.delete(contract.getId());
		revivalEntityModel.delete(contract.getId());
		terminationEntityModel.delete(contract.getId());
		recontractEntityModel.delete(contract.getId());
		contractEntityModel.delete(contract.getId());
	}
}
