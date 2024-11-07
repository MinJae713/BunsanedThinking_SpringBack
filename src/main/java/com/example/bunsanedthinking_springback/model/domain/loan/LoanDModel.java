package com.example.bunsanedthinking_springback.model.domain.loan;

import com.example.bunsanedthinking_springback.entity.loan.Loan;
import com.example.bunsanedthinking_springback.repository.LoanMapper;
import com.example.bunsanedthinking_springback.vo.LoanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanDModel {
    @Autowired
    private LoanMapper loanMapper;
    public Loan getById(int id) {
        // 추상타입이라 반환 불가
        return null;
    }
    public List<Loan> getAll() {
        // 추상타입이라 반환 불가
        return null;
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
