package com.example.bunsanedthinking_springback.model.domain.fixedDeposit;

import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
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
public class FixedDepositDModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private LoanMapper loanMapper;
	@Autowired
	private FixedDepositMapper fixedDepositMapper;

	public FixedDeposit getById(int id) {
		ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
		if (productVO == null)
			return null;
		LoanVO loanVO = loanMapper.findById_LoanManagement(id).orElse(null);
		if (loanVO == null)
			return null;
		FixedDepositVO fixedDepositVO = fixedDepositMapper.getById_Customer(id).orElse(null);
		if (fixedDepositVO == null)
			return null;
		int minimumAmount = fixedDepositVO.getMinimum_amount();
		return new FixedDeposit(productVO, loanVO, minimumAmount);
	}

	public List<FixedDeposit> getAll() {
		List<FixedDeposit> fixedDeposits = new ArrayList<FixedDeposit>();
		fixedDepositMapper.getAll_Customer()
			.forEach(e -> fixedDeposits.add(getById(e.getProduct_id())));
		return fixedDeposits;
	}

	public Integer getMaxId() {
		return fixedDepositMapper.getMaxId();
	}

	public void add(FixedDeposit fixedDeposit) {
		if (fixedDeposit == null) return;
		if (fixedDepositMapper.getById_Customer(fixedDeposit.getId()).isPresent()) return;
		productMapper.insert_LoanManagement(fixedDeposit.findProductVO());
		loanMapper.insert_LoanManagement(fixedDeposit.findLoanVO());
		fixedDepositMapper.insert_LoanManagement(fixedDeposit.findVO());
	}

	public void update(FixedDeposit fixedDeposit) {
		if (fixedDeposit == null) return;
		if (fixedDepositMapper.getById_Customer(fixedDeposit.getId()).isEmpty()) return;
		fixedDepositMapper.update_LoanManagement(fixedDeposit.findVO());
		loanMapper.update_LoanManagement(fixedDeposit.findLoanVO());
		productMapper.update_LoanManagement(fixedDeposit.findProductVO());
	}

	public void delete(int id) {
		if (fixedDepositMapper.getById_Customer(id).isEmpty()) return;
		fixedDepositMapper.delete_LoanManagement(id);
		loanMapper.delete_LoanManagement(id);
		productMapper.delete_LoanManagement(id);
	}
}
