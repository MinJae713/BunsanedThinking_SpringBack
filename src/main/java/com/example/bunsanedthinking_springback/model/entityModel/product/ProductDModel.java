package com.example.bunsanedthinking_springback.model.entityModel.product;

import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.model.entityModel.insurance.InsuranceDModel;
import com.example.bunsanedthinking_springback.model.entityModel.loan.LoanDModel;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

	public Integer getMaxId() {
		// 이건 무조건 loan나와서 의미 없을듯염
		return 0;
	}

	public void add(Product product) {
		if (product == null) return;
		else if (product instanceof Insurance) insuranceDModel.add((Insurance) product);
		else if (product instanceof Loan) loanDModel.add((Loan) product);
	}

	public void update(Product product) {
		if (product == null) return;
		else if (product instanceof Insurance) insuranceDModel.update((Insurance) product);
		else if (product instanceof Loan) loanDModel.update((Loan) product);
	}

	public void delete(int id) {
		Product product = getById(id);
		if (product == null) return;
		else if (product instanceof Insurance) insuranceDModel.delete(id);
		else if (product instanceof Loan) loanDModel.delete(id);
	}
}
