package com.example.bunsanedthinking_springback.model.domain.loan;

import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.model.domain.collateral.CollateralDModel;
import com.example.bunsanedthinking_springback.model.domain.fixedDeposit.FixedDepositDModel;
import com.example.bunsanedthinking_springback.model.domain.insuranceContract.InsuranceContractDModel;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanDModel {
    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private CollateralDModel collateralDModel;
    @Autowired
    private FixedDepositDModel fixedDepositDModel;
    @Autowired
    private InsuranceContractDModel insuranceContractDModel;
    public Loan getById(int id) {
        Loan loan = collateralDModel.getById(id);
        if (loan != null) return loan;
        loan = fixedDepositDModel.getById(id);
        if (loan != null) return loan;
        loan = insuranceContractDModel.getById(id);
        if (loan != null) return loan;
        return null; // 입력 아이디가 세 종류 모두 아닌 경우임
    }
    public List<Loan> getAll() {
        List<Loan> loans = new ArrayList<Loan>();
        loanMapper.getAll_Customer().stream().forEach(e -> loans.add(getById(e.getProduct_id())));
        return loans;
    }
    public int getMaxId() {
        return loanMapper.getMaxId_LoanManagement();
    }
    public void add(LoanVO loanVO) {
        loanMapper.insert_LoanManagement(loanVO);
    }
    public void update(LoanVO loanVO) {
        loanMapper.update_LoanManagement(loanVO);
    }
    public void delete(int id) {
        loanMapper.delete_LoanManagement(id);
    }
}
