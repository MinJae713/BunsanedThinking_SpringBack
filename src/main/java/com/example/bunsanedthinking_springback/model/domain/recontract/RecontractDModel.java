package com.example.bunsanedthinking_springback.model.domain.recontract;

import com.example.bunsanedthinking_springback.entity.recontract.Recontract;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.RecontractMapper;
import com.example.bunsanedthinking_springback.vo.RecontractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecontractDModel {
    @Autowired
    private RecontractMapper recontractMapper;
    @Autowired
    private ContractDModel contractDModel;
    public Recontract getById(int id) {
        return recontractMapper.
                getById_Customer(id).orElse(null).
                getEntity(contractDModel.getById(id));
    }
    public List<Recontract> getAll() {
        List<Recontract> recontracts = new ArrayList<Recontract>();
        recontractMapper.getAll_ContractManagement().
                stream().forEach(
                        e -> recontracts.add(getById(e.getContract_id()))
                );
        return recontracts;
    }
    public int getMaxId() {
        return recontractMapper.getMaxId();
    }
    public void add(RecontractVO recontractVO) {
        recontractMapper.insert(recontractVO);
    }
    public void update(RecontractVO recontractVO) {
        recontractMapper.update(recontractVO);
    }
    public void delete(int id) {
        recontractMapper.deleteById(id);
    }
}
