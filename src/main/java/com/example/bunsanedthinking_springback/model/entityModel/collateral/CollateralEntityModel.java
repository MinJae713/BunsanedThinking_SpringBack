package com.example.bunsanedthinking_springback.model.entityModel.collateral;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.endorsement.EndorsementEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.recontract.RecontractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.revival.RevivalEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.termination.TerminationEntityModel;
import com.example.bunsanedthinking_springback.repository.CollateralMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.CollateralVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollateralEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private CollateralMapper collateralMapper;
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


	public Collateral getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		LoanVO loanVO = loanMapper.getById(id).orElse(null);
		if (loanVO == null)
			return null;
		CollateralVO collateralVO = collateralMapper.getById(id).orElse(null);
		if (collateralVO == null)
			return null;
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getProductId() == id).toList();
		return collateralVO.getEntity(productVO, loanVO, contracts);
//		return new Collateral(productVO, loanVO, collateralType, minimumValue);
	}

	public List<Collateral> getAll() {
		List<Collateral> collaterals = new ArrayList<Collateral>();
		collateralMapper.getAll()
			.forEach(e -> collaterals.add(getById(e.getProduct_id())));
		return collaterals;
	}

	public Integer getMaxId() {
		return collateralMapper.getMaxId();
	}

	public void add(Collateral collateral) {
		if (collateral == null) return;
		if (collateralMapper.getById(collateral.getId()).isPresent()) return;
		productMapper.insert(collateral.findProductVO());
		loanMapper.insert(collateral.findLoanVO());
		collateralMapper.insert(collateral.findVO());
		if (collateral.getContractList() == null) return;
		collateral.getContractList().forEach(e -> contractEntityModel.add(e));
	}

	public void update(Collateral collateral) {
		if (collateral == null) return;
		if (collateralMapper.getById(collateral.getId()).isEmpty()) return;
		List<Contract> contracts = collateral.getContractList();
		if (contracts != null) contracts.forEach(e -> contractEntityModel.update(e));
		collateralMapper.update(collateral.findVO());
		loanMapper.update(collateral.findLoanVO());
		productMapper.update(collateral.findProductVO());
	}

	public void delete(int id) {
		if (collateralMapper.getById(id).isEmpty()) return;
		contractEntityModel.getAll().stream().
				filter(e -> e.getProductId() == id).
				forEach(e -> deleteContract(e));
		collateralMapper.deleteById(id);
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
