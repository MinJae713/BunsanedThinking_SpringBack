package com.example.bunsanedthinking_springback.entity.product;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.vo.ProductVO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 20-5-2024 ���� 7:52:27
 */

@NoArgsConstructor
@Data
public abstract class Product implements Cloneable {

	private int id;
	private int maximumMoney;
	private String name;
	private List<Contract> contractList;
	private List<Counsel> counselList;

	public ProductVO findProductVO() {
		return new ProductVO(id, name, maximumMoney);
	}

	public abstract Product clone();
}