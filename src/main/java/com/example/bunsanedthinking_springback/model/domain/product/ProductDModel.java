package com.example.bunsanedthinking_springback.model.domain.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.model.domain.insurance.InsuranceDModel;
import com.example.bunsanedthinking_springback.model.domain.loan.LoanDModel;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import com.example.bunsanedthinking_springback.vo.ProductVO;

@Service
public class ProductDModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceDModel insuranceDModel;
	@Autowired
	private LoanDModel loanDModel;

	public Product getById(int id) {
		Product product = insuranceDModel.getById(id);
		if (product != null)
			return product;
		product = loanDModel.getById(id);
		return product;
	}

	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();
		productMapper.getAll_LoanManagement()
			.forEach(e -> products.add(getById(e.getId())));
		return products;
	}

	public int getMaxId() {
		// 이건 무조건 loan나와서 의미 없을듯염
		return 0;
	}

	public void add(ProductVO productVO) {
		productMapper.insert_LoanManagement(productVO);
	}

	public void update(ProductVO productVO) {
		productMapper.update_LoanManagement(productVO);
	}

	public void delete(int id) {
		productMapper.delete_LoanManagement(id);
	}
}
