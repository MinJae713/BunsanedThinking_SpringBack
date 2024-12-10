package com.example.bunsanedthinking_springback.model.entityModel.injury;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.insurance.Injury;
import com.example.bunsanedthinking_springback.global.common.ReadOnly;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.InjuryVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InjuryEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private InjuryMapper injuryMapper;
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
	public Injury getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById(id).orElse(null);
		if (insuranceVO == null)
			return null;
		InjuryVO injuryVO = injuryMapper.getById(id).orElse(null);
		if (injuryVO == null)
			return null;
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getProductId() == id).toList();
		List<Counsel> counsels = counselEntityModel.getAll().
				stream().filter(e -> e.getProductID() == id).toList();
		return injuryVO.getEntity(productVO, insuranceVO, contracts, counsels);
	}
	@ReadOnly
	public List<Injury> getAll() {
		List<Injury> injuries = new ArrayList<Injury>();
		injuryMapper.getAll()
			.forEach(e -> injuries.add(getById(e.getProduct_id())));
		return injuries;
	}
	@ReadOnly
	public Integer getMaxId() {
		return injuryMapper.getMaxId();
	}

	public void add(Injury injury) {
		if (injury == null) return;
		if (injuryMapper.getById(injury.getId()).isPresent()) return;
		productMapper.insert(injury.findProductVO());
		insuranceMapper.insert(injury.findInsuranceVO());
		injuryMapper.insert(injury.findVO());
		if (injury.getCounselList() != null)
			injury.getCounselList().forEach(e -> counselEntityModel.add(e));
		if (injury.getContractList() != null)
			injury.getContractList().forEach(e -> contractEntityModel.add(e));

	}

	public void update(Injury injury) {
		if (injury == null) return;
		if (injuryMapper.getById(injury.getId()).isEmpty()) return;
		List<Counsel> counsels = injury.getCounselList();
		if (counsels != null) counsels.forEach(e -> counselEntityModel.update(e));
		List<Contract> contracts = injury.getContractList();
		if (contracts != null) contracts.forEach(e -> contractEntityModel.update(e));
		injuryMapper.update(injury.findVO());
		insuranceMapper.update(injury.findInsuranceVO());
		productMapper.update(injury.findProductVO());
	}

	public void delete(int id) {
		// 참조 무결성 제약조건 생각해서 injury, insurnace, product 순서대로 데이터 삭제
		if (injuryMapper.getById(id).isEmpty()) return;
		contractEntityModel.getAll().stream().
				filter(e -> e.getProductId() == id).
				forEach(this::deleteContract);
		counselEntityModel.getAll().stream().
				filter(e -> e.getProductID() == id).
				forEach(e -> counselEntityModel.delete(e.getId()));
		injuryMapper.deleteById(id);
		insuranceMapper.deleteById(id);
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
