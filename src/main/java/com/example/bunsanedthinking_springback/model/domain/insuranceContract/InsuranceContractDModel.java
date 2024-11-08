package com.example.bunsanedthinking_springback.model.domain.insuranceContract;

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
public class InsuranceContractDModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private InsuranceContractMapper insuranceContractMapper;

	public InsuranceContract getById(int id) {
		ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
		if (productVO == null)
			return null;
		LoanVO loanVO = loanMapper.findById_LoanManagement(id).orElse(null);
		if (loanVO == null)
			return null;
		InsuranceContractVO insuranceContractVO = insuranceContractMapper.getById_Customer(id).orElse(null);
		if (insuranceContractVO == null)
			return null;
		int insuranceId = insuranceContractVO.getInsurance_id();
		return new InsuranceContract(productVO, loanVO, insuranceId);
	}

	public List<InsuranceContract> getAll() {
		List<InsuranceContract> insuranceContracts = new ArrayList<InsuranceContract>();
		insuranceContractMapper.getAll_Customer()
			.forEach(e -> insuranceContracts.add(getById(e.getProduct_id())));
		return insuranceContracts;
	}

	public Integer getMaxId() {
		return insuranceContractMapper.getMaxId();
	}

	public void add(InsuranceContract insuranceContract) {
		if (insuranceContract == null) return;
		if (insuranceContractMapper.getById_Customer(insuranceContract.getId()).isPresent()) return;
		productMapper.insert_LoanManagement(insuranceContract.getProductVO());
		loanMapper.insert_LoanManagement(insuranceContract.getLoanVO());
		insuranceContractMapper.insert_LoanManagement(insuranceContract.getVO());
	}

	public void update(InsuranceContract insuranceContract) {
		if (insuranceContract == null) return;
		if (insuranceContractMapper.getById_Customer(insuranceContract.getId()).isEmpty()) return;
		insuranceContractMapper.update_LoanManagement(insuranceContract.getVO());
		loanMapper.update_LoanManagement(insuranceContract.getLoanVO());
		productMapper.update_LoanManagement(insuranceContract.getProductVO());
	}

	public void delete(int id) {
		if (insuranceContractMapper.getById_Customer(id).isEmpty()) return;
		insuranceContractMapper.delete_LoanManagement(id);
		loanMapper.delete_LoanManagement(id);
		productMapper.delete_LoanManagement(id);
	}
}
