package com.example.bunsanedthinking_springback.model.entityModel.insuranceContract;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.InsuranceContractVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceContractEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private InsuranceContractMapper insuranceContractMapper;
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


	public InsuranceContract getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		LoanVO loanVO = loanMapper.getById(id).orElse(null);
		if (loanVO == null)
			return null;
		InsuranceContractVO insuranceContractVO = insuranceContractMapper.getById(id).orElse(null);
		if (insuranceContractVO == null)
			return null;
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getProductId() == id).toList();
		List<Counsel> counsels = counselEntityModel.getAll().
				stream().filter(e -> e.getProductID() == id).toList();
		return insuranceContractVO.getEntity(productVO, loanVO, contracts, counsels);
//		return new InsuranceContract(productVO, loanVO, insuranceId);
	}

	public List<InsuranceContract> getAll() {
		List<InsuranceContract> insuranceContracts = new ArrayList<InsuranceContract>();
		insuranceContractMapper.getAll()
			.forEach(e -> insuranceContracts.add(getById(e.getProduct_id())));
		return insuranceContracts;
	}

	public Integer getMaxId() {
		return insuranceContractMapper.getMaxId();
	}

	public void add(InsuranceContract insuranceContract) {
		if (insuranceContract == null) return;
		if (insuranceContractMapper.getById(insuranceContract.getId()).isPresent()) return;
		productMapper.insert(insuranceContract.findProductVO());
		loanMapper.insert(insuranceContract.findLoanVO());
		insuranceContractMapper.insert(insuranceContract.findVO());
		if (insuranceContract.getCounselList() != null)
			insuranceContract.getCounselList().forEach(e -> counselEntityModel.add(e));
		if (insuranceContract.getContractList() != null)
			insuranceContract.getContractList().forEach(e -> contractEntityModel.add(e));
	}

	public void update(InsuranceContract insuranceContract) {
		if (insuranceContract == null) return;
		if (insuranceContractMapper.getById(insuranceContract.getId()).isEmpty()) return;
		List<Counsel> counsels = insuranceContract.getCounselList();
		if (counsels != null) counsels.forEach(e -> counselEntityModel.update(e));
		List<Contract> contracts = insuranceContract.getContractList();
		if (contracts != null) contracts.forEach(e -> contractEntityModel.update(e));
		insuranceContractMapper.update(insuranceContract.findVO());
		loanMapper.update(insuranceContract.findLoanVO());
		productMapper.update(insuranceContract.findProductVO());
	}

	public void delete(int id) {
		if (insuranceContractMapper.getById(id).isEmpty()) return;
		contractEntityModel.getAll().stream().
				filter(e -> e.getProductId() == id).
				forEach(this::deleteContract);
		counselEntityModel.getAll().stream().
				filter(e -> e.getProductID() == id).
				forEach(e -> counselEntityModel.delete(e.getId()));
		insuranceContractMapper.deleteById(id);
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
