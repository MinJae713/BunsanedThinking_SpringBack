package com.example.bunsanedthinking_springback.entity.product;

import java.util.List;

import com.example.bunsanedthinking_springback.entity.contract.Contract;
import com.example.bunsanedthinking_springback.entity.counsel.Counsel;
import com.example.bunsanedthinking_springback.vo.ProductVO;

import lombok.Data;
import lombok.NoArgsConstructor;

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
	// contractList 신규 추가 (1-n 관계 명시)

	public ProductVO findProductVO() {
		return new ProductVO(id, name, maximumMoney);
	}

	public abstract Product clone();
}