package com.example.bunsanedthinking_springback.vo;

import com.example.bunsanedthinking_springback.entity.product.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
	private int id;
	private String name;
	private int maximum_money;

	public static ProductVO from(Product product) {
		return new ProductVO(product.getId(), product.getName(), product.getMaximumMoney());
	}
}
