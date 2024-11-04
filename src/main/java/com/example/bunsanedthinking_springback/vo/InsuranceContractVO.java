package com.example.bunsanedthinking_springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceContractVO {
	private int product_id; // 테이블 이름 따라갑니다 - 수정함
	private int insurance_id;
}
