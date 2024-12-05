package com.example.bunsanedthinking_springback.entity.paymentDetail;

import com.example.bunsanedthinking_springback.vo.AdditionalAllowanceVO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:40
 */
@NoArgsConstructor
@Data
public class AdditionalAllowance extends PaymentDetail {

	private AdditionalAllowanceType type;

	public AdditionalAllowanceVO findVO() {
		return new AdditionalAllowanceVO(getId(), type.ordinal());
	}
}