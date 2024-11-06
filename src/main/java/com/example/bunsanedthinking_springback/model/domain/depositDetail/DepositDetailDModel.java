package com.example.bunsanedthinking_springback.model.domain.depositDetail;

import com.example.bunsanedthinking_springback.entity.depositDetail.DepositDetail;
import com.example.bunsanedthinking_springback.repository.DepositDetailMapper;
import com.example.bunsanedthinking_springback.vo.DepositDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositDetailDModel {
    @Autowired
    private DepositDetailMapper depositDetailMapper;
    public DepositDetail getById(int id) {
        return depositDetailMapper.findById_FinancialAccountant(id).orElse(null).getEntity();
    }
    public List<DepositDetail> getAll() {
        List<DepositDetail> depositDetails = new ArrayList<DepositDetail>();
        depositDetailMapper.getAll_ContractManagement().stream().forEach(e -> depositDetails.add(e.getEntity()));
        return depositDetails;
    }
    public int getMaxId() {
        return depositDetailMapper.getLastId_Customer();
    }
    public void add(DepositDetailVO depositDetailVO) {
        depositDetailMapper.add_Customer(depositDetailVO);
    }
    public void update(DepositDetailVO depositDetailVO) {
        depositDetailMapper.update(depositDetailVO);
    }
    public void delete(int id) {
        depositDetailMapper.deleteById(id);
    }
}
