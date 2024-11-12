package com.example.bunsanedthinking_springback.model.entityModel.fixedDeposit;

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
public class FixedDepositEntityModel {
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
		LoanVO loanVO = loanMapper.getById(id).orElse(null);
		if (loanVO == null)
			return null;
		FixedDepositVO fixedDepositVO = fixedDepositMapper.getById(id).orElse(null);
		if (fixedDepositVO == null)
			return null;
		int minimumAmount = fixedDepositVO.getMinimum_amount();
		return new FixedDeposit(productVO, loanVO, minimumAmount);
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
		productMapper.insert_LoanManagement(fixedDeposit.findProductVO());
		loanMapper.insert(fixedDeposit.findLoanVO());
		fixedDepositMapper.insert(fixedDeposit.findVO());
	}

	public void update(FixedDeposit fixedDeposit) {
		if (fixedDeposit == null) return;
		if (fixedDepositMapper.getById(fixedDeposit.getId()).isEmpty()) return;
		fixedDepositMapper.update(fixedDeposit.findVO());
		loanMapper.update(fixedDeposit.findLoanVO());
		productMapper.update_LoanManagement(fixedDeposit.findProductVO());
	}

	public void delete(int id) {
		if (fixedDepositMapper.getById(id).isEmpty()) return;
		fixedDepositMapper.deleteById(id);
		loanMapper.deleteById(id);
		productMapper.delete_LoanManagement(id);
	}
}
