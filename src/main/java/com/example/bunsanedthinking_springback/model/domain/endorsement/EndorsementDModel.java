package com.example.bunsanedthinking_springback.model.domain.endorsement;

import com.example.bunsanedthinking_springback.entity.endorsment.Endorsement;
import com.example.bunsanedthinking_springback.model.domain.contract.ContractDModel;
import com.example.bunsanedthinking_springback.repository.EndorsementMapper;
import com.example.bunsanedthinking_springback.vo.EndorsementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EndorsementDModel {
    @Autowired
    private EndorsementMapper endorsementMapper;
    @Autowired
    private ContractDModel contractDModel;
    public Endorsement getById(int id) {
        return endorsementMapper.
                getById_Customer(id).
                orElse(null).
                getEntity(contractDModel.getById(id));
    }
    public List<Endorsement> getAll() {
        List<Endorsement> endorsements = new ArrayList<>();
        endorsementMapper.getAll_ContractManagement().
                stream().forEach(
                        e -> endorsements.add(getById(e.getContract_id()))
                );
        return endorsements;
    }
    public int getMaxId() {
        return endorsementMapper.getMaxId();
    }
    public void add(EndorsementVO endorsementVO) {
        endorsementMapper.insert(endorsementVO);
    }
    public void update(EndorsementVO endorsementVO) {
        endorsementMapper.update(endorsementVO);
    }
    public void delete(int id) {
        endorsementMapper.deleteById(id);
    }
}
