package com.example.bunsanedthinking_springback.entity.loan;

import com.example.bunsanedthinking_springback.entity.product.Product;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import lombok.Data;

/**
 * @author ����ȯ
 * @version 1.0
 * @created 27-5-2024 ���� 4:40:42
 */
@Data
public abstract class Loan extends Product {

	private int interestRate;
	private LoanType loanType;
	private int minimumAsset;
	private int monthlyIncome;

	public static final int LOAN_SERIAL_NUMBER = 200;
	
	public Loan(){

	}

	public LoanVO findLoanVO() {
		return new LoanVO(getId(), loanType.ordinal(),
				minimumAsset, monthlyIncome, interestRate);
	}

	public void buy(){

	}

	public void view(){

	}
}