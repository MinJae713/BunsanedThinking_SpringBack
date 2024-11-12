package com.example.bunsanedthinking_springback.model.entityModel.insuranceContract;

import com.example.bunsanedthinking_springback.entity.loan.InsuranceContract;
import com.example.bunsanedthinking_springback.repository.InsuranceContractMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
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
		int insuranceId = insuranceContractVO.getInsurance_id();
		return new InsuranceContract(productVO, loanVO, insuranceId);
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
	}

	public void update(InsuranceContract insuranceContract) {
		if (insuranceContract == null) return;
		if (insuranceContractMapper.getById(insuranceContract.getId()).isEmpty()) return;
		insuranceContractMapper.update(insuranceContract.findVO());
		loanMapper.update(insuranceContract.findLoanVO());
		productMapper.update(insuranceContract.findProductVO());
	}

	public void delete(int id) {
		if (insuranceContractMapper.getById(id).isEmpty()) return;
		insuranceContractMapper.deleteById(id);
		loanMapper.deleteById(id);
		productMapper.deleteById(id);
	}
}
