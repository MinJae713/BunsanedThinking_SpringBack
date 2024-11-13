package com.example.bunsanedthinking_springback.model.entityModel.product;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.insurance.Insurance;
import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.model.entityModel.contract.ContractEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.insurance.InsuranceEntityModel;
import com.example.bunsanedthinking_springback.model.entityModel.loan.LoanEntityModel;
import com.example.bunsanedthinking_springback.repository.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductEntityModel {
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private InsuranceEntityModel insuranceEntityModel;
	@Autowired
	private LoanEntityModel loanEntityModel;
	@Autowired
	private ContractEntityModel contractEntityModel;

	public Product getById(int id) {
		Product product = insuranceEntityModel.getById(id);
		if (product != null)
			return product;
		product = loanEntityModel.getById(id);
		return product;
	}

	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();
		productMapper.getAll()
			.forEach(e -> products.add(getById(e.getId())));
		return products;
	}

	public Integer getMaxId() {
		// 이건 무조건 loan나와서 의미 없을듯염
		return 0;
	}

	public void add(Product product) {
		if (product == null) return;
		else if (product instanceof Insurance) insuranceEntityModel.add((Insurance) product);
		else if (product instanceof Loan) loanEntityModel.add((Loan) product);
	}

	public void update(Product product) {
		if (product == null) return;
		else if (product instanceof Insurance) insuranceEntityModel.update((Insurance) product);
		else if (product instanceof Loan) loanEntityModel.update((Loan) product);
	}

	public void delete(int id) {
		Product product = getById(id);
		if (product == null) return;
		for (Contract contract : contractEntityModel.getAll())
			if (contract.getProductId() == id)
				contractEntityModel.delete(contract.getId());
		if (product instanceof Insurance) insuranceEntityModel.delete(id);
		else if (product instanceof Loan) loanEntityModel.delete(id);
	}
}
