package com.example.bunsanedthinking_springback.model.entityModel.disease;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.counsel.CounselEntityModel;
import com.example.bunsanedthinking_springback.repository.*;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;
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


	public Disease getById(int id) {
		ProductVO productVO = productMapper.getById(id).orElse(null);
		if (productVO == null)
			return null;
		InsuranceVO insuranceVO = insuranceMapper.getById(id).orElse(null);
		if (insuranceVO == null)
			return null;
		DiseaseVO diseaseVO = diseaseMapper.getById(id).orElse(null);
		if (diseaseVO == null)
			return null;
		List<Contract> contracts = contractEntityModel.getAll().
				stream().filter(e -> e.getProductId() == id).toList();
		List<Counsel> counsels = counselEntityModel.getAll().
				stream().filter(e -> e.getProductID() == id).toList();
		return diseaseVO.getEntity(productVO, insuranceVO, contracts, counsels);
	}

	public List<Disease> getAll() {
		List<Disease> diseases = new ArrayList<Disease>();
		diseaseMapper.getAll()
			.forEach(e -> diseases.add(getById(e.getProduct_id())));
		return diseases;
	}

	public Integer getMaxId() {
		return diseaseMapper.getMaxId();
	}

	public void add(Disease disease) {
		if (disease == null) return;
		if (diseaseMapper.getById(disease.getId()).isPresent()) return;
		productMapper.insert(disease.findProductVO());
		insuranceMapper.insert(disease.findInsuranceVO());
		diseaseMapper.insert(disease.findVO());
		if (disease.getCounselList() != null)
			disease.getCounselList().forEach(e -> counselEntityModel.add(e));
		if (disease.getContractList() != null)
			disease.getContractList().forEach(e -> contractEntityModel.add(e));

	}

	public void update(Disease disease) {
		if (disease == null) return;
		if (diseaseMapper.getById(disease.getId()).isEmpty()) return;
		List<Counsel> counsels = disease.getCounselList();
		if (counsels != null) counsels.forEach(e -> counselEntityModel.update(e));
		List<Contract> contracts = disease.getContractList();
		if (contracts != null) contracts.forEach(e -> contractEntityModel.update(e));
		diseaseMapper.update(disease.findVO());
		insuranceMapper.update(disease.findInsuranceVO());
		productMapper.update(disease.findProductVO());
	}

	public void delete(int id) {
		if (diseaseMapper.getById(id).isEmpty()) return;
		contractEntityModel.getAll().stream().
				filter(e -> e.getProductId() == id).
				forEach(e -> deleteContract(e));
		counselEntityModel.getAll().stream().
				filter(e -> e.getProductID() == id).
				forEach(e -> counselEntityModel.delete(e.getId()));
		diseaseMapper.deleteById(id);
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
