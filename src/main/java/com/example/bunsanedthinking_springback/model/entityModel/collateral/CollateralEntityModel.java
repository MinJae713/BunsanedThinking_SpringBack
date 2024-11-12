package com.example.bunsanedthinking_springback.model.entityModel.collateral;

import com.example.bunsanedthinking_springback.entity.loan.Collateral;
import com.example.bunsanedthinking_springback.entity.loan.CollateralType;
import com.example.bunsanedthinking_springback.repository.CollateralMapper;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
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

	public Collateral getById(int id) {
		ProductVO productVO = productMapper.getById_Customer(id).orElse(null);
		if (productVO == null)
			return null;
		LoanVO loanVO = loanMapper.getById(id).orElse(null);
		if (loanVO == null)
			return null;
		CollateralVO collateralVO = collateralMapper.getById(id).orElse(null);
		if (collateralVO == null)
			return null;
		CollateralType collateralType = CollateralType.indexOf(collateralVO.getCollateral_type());
		int minimumValue = collateralVO.getMinimum_value();
		return new Collateral(productVO, loanVO, collateralType, minimumValue);
	}

	public List<Collateral> getAll() {
		List<Collateral> collaterals = new ArrayList<Collateral>();
		collateralMapper.getAll()
			.forEach(e -> collaterals.add(getById(e.getProduct_id())));
		return collaterals;
	}

	public Integer getMaxId() {
		return collateralMapper.getMaxId();
	}

	public void add(Collateral collateral) {
		if (collateral == null) return;
		if (collateralMapper.getById(collateral.getId()).isPresent()) return;
		productMapper.insert_LoanManagement(collateral.findProductVO());
		loanMapper.insert(collateral.findLoanVO());
		collateralMapper.insert(collateral.findVO());
	}

	public void update(Collateral collateral) {
		if (collateral == null) return;
		if (collateralMapper.getById(collateral.getId()).isEmpty()) return;
		collateralMapper.update(collateral.findVO());
		loanMapper.update(collateral.findLoanVO());
		productMapper.update_LoanManagement(collateral.findProductVO());
	}

	public void delete(int id) {
		if (collateralMapper.getById(id).isEmpty()) return;
		collateralMapper.delete(id);
		loanMapper.deleteById(id);
		productMapper.delete_LoanManagement(id);
	}
}
