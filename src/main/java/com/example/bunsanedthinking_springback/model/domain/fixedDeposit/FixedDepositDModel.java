package com.example.bunsanedthinking_springback.model.domain.fixedDeposit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.loan.FixedDeposit;
import com.example.bunsanedthinking_springback.repository.FixedDepositMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.FixedDepositVO;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import com.example.bunsanedthinking_springback.vo.ProductVO;

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

	public void add(FixedDepositVO fixedDepositVO) {
		fixedDepositMapper.insert_LoanManagement(fixedDepositVO);
	}

	public void update(FixedDepositVO fixedDepositVO) {
		fixedDepositMapper.update_LoanManagement(fixedDepositVO);
	}

	public void delete(int id) {
		fixedDepositMapper.delete_LoanManagement(id);
	}
}
