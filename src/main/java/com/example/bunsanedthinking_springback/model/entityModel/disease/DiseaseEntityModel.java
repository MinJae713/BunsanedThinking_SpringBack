package com.example.bunsanedthinking_springback.model.entityModel.disease;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.insurance.Disease;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.endorsement.EndorsementEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.recontract.RecontractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.revival.RevivalEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.termination.TerminationEntityModel;
import com.example.bunsanedthinking_springback.repository.DiseaseMapper;
import com.example.bunsanedthinking_springback.repository.InsuranceMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.DiseaseVO;
import com.example.bunsanedthinking_springback.vo.InsuranceVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;

@Service
public class DiseaseEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceMapper insuranceMapper;
	@Autowired
	private DiseaseMapper diseaseMapper;
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
		return diseaseVO.getEntity(productVO, insuranceVO, contracts);
//		return new Disease(productVO, insuranceVO, diseaseName, diseaseLimit, surgeriesLimit);
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
		if (disease.getContractList() == null) return;
		disease.getContractList().forEach(e -> contractEntityModel.add(e));
	}

	public void update(Disease disease) {
		if (disease == null) return;
		if (diseaseMapper.getById(disease.getId()).isEmpty()) return;
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
		diseaseMapper.deleteById(id);
		insuranceMapper.deleteById(id);
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
