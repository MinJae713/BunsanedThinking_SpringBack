package com.example.bunsanedthinking_springback.model.entityModel.collateral;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.repository.*;
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
	private CounselEntityModel counselEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;
	@Autowired
	private EndorsementMapper endorsementMapper;
	@Autowired
	private RevivalMapper revivalMapper;
	@Autowired
	private TerminationMapper terminationMapper;
	@Autowired
	private RecontractMapper recontractMapper;
	@ReadOnly
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
		List<Counsel> counsels = counselEntityModel.getAll().
				stream().filter(e -> e.getProductID() == id).toList();
		return collateralVO.getEntity(productVO, loanVO, contracts, counsels);
//		return new Collateral(productVO, loanVO, collateralType, minimumValue);
	}
	@ReadOnly
	public List<Collateral> getAll() {
		List<Collateral> collaterals = new ArrayList<Collateral>();
		collateralMapper.getAll()
			.forEach(e -> collaterals.add(getById(e.getProduct_id())));
		return collaterals;
	}
	@ReadOnly
	public Integer getMaxId() {
		return collateralMapper.getMaxId();
	}

	public void add(Collateral collateral) {
		if (collateral == null) return;
		if (collateralMapper.getById(collateral.getId()).isPresent()) return;
		productMapper.insert(collateral.findProductVO());
		loanMapper.insert(collateral.findLoanVO());
		collateralMapper.insert(collateral.findVO());
		if (collateral.getCounselList() != null)
			collateral.getCounselList().forEach(e -> counselEntityModel.add(e));
		if (collateral.getContractList() != null)
			collateral.getContractList().forEach(e -> contractEntityModel.add(e));
	}

	public void update(Collateral collateral) {
		if (collateral == null) return;
		if (collateralMapper.getById(collateral.getId()).isEmpty()) return;
		List<Counsel> counsels = collateral.getCounselList();
		if (counsels != null) counsels.forEach(e -> counselEntityModel.update(e));
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
		counselEntityModel.getAll().stream().
				filter(e -> e.getProductID() == id).
				forEach(e -> counselEntityModel.delete(e.getId()));
		collateralMapper.deleteById(id);
		loanMapper.deleteById(id);
		productMapper.deleteById(id);
	}

	private void deleteContract(Contract contract) {
		endorsementMapper.deleteById(contract.getId());
		revivalMapper.deleteById(contract.getId());
		terminationMapper.deleteById(contract.getId());
		recontractMapper.deleteById(contract.getId());
		contractEntityModel.delete(contract.getId());
	}
}
